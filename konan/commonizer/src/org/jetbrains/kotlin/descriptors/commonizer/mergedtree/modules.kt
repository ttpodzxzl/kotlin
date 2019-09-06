/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.descriptors.commonizer.mergedtree

import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.commonizer.CommonizedGroupMap
import org.jetbrains.kotlin.descriptors.commonizer.mergedtree.ir.ModuleNode
import org.jetbrains.kotlin.descriptors.commonizer.mergedtree.ir.buildModuleNode
import org.jetbrains.kotlin.descriptors.commonizer.toList
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.resolve.scopes.ChainedMemberScope
import org.jetbrains.kotlin.resolve.scopes.MemberScope
import org.jetbrains.kotlin.utils.alwaysTrue

internal fun mergeModules(modules: List<ModuleDescriptor?>): ModuleNode {
    val node = buildModuleNode(modules)

    val packageMemberScopesMap = CommonizedGroupMap<FqName, MemberScope>(modules.size)

    modules.forEachIndexed { index, module ->
        module?.collectNonEmptyPackageMemberScopes { packageFqName, memberScope ->
            packageMemberScopesMap[packageFqName][index] = memberScope
        }
    }

    for ((packageFqName, packageMemberScopesGroup) in packageMemberScopesMap) {
        node.packages += mergePackages(packageFqName, packageMemberScopesGroup.toList())
    }

    return node
}

// collects member scopes for every non-empty package provided by this module
internal fun ModuleDescriptor.collectNonEmptyPackageMemberScopes(collector: (FqName, MemberScope) -> Unit) {
    // we don's need to process fragments from other modules which are the dependencies of this module, so
    // let's use the appropriate package fragment provider
    val packageFragmentProvider = (this as ModuleDescriptorImpl).packageFragmentProviderForModuleContentWithoutDependencies

    fun recurse(packageFqName: FqName) {
        val ownPackageFragments = packageFragmentProvider.getPackageFragments(packageFqName)
        val ownPackageMemberScopes = ownPackageFragments.asSequence()
            .map { it.getMemberScope() }
            .filter { it != MemberScope.Empty }
            .toList(ownPackageFragments.size)

        if (ownPackageMemberScopes.isNotEmpty()) {
            // don't include subpackages into chained member scope
            val memberScope = ChainedMemberScope.create(
                "package member scope for $packageFqName in $name",
                ownPackageMemberScopes
            )
            collector(packageFqName, memberScope)
        }

        packageFragmentProvider.getSubPackagesOf(packageFqName, alwaysTrue()).map { recurse(it) }
    }

    recurse(FqName.ROOT)
}
