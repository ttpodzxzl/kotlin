/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.fir.visitors

import org.jetbrains.kotlin.fir.*
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.impl.FirModifiableClass
import org.jetbrains.kotlin.fir.declarations.impl.FirModifiableFunction
import org.jetbrains.kotlin.fir.expressions.*
import org.jetbrains.kotlin.fir.expressions.impl.*
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference
import org.jetbrains.kotlin.fir.types.*


/** This file generated by :compiler:fir:tree:generateVisitors. DO NOT MODIFY MANUALLY! */
abstract class FirVisitorVoid : FirVisitor<Unit, Nothing?>() {
    abstract fun visitElement(element: FirElement)

    open fun visitCatch(catch: FirCatch) {
        visitElement(catch, null)
    }

    open fun visitDeclaration(declaration: FirDeclaration) {
        visitElement(declaration, null)
    }

    open fun <F : FirCallableDeclaration<F>> visitCallableDeclaration(callableDeclaration: FirCallableDeclaration<F>) {
        visitElement(callableDeclaration, null)
    }

    open fun <F : FirCallableMemberDeclaration<F>> visitCallableMemberDeclaration(callableMemberDeclaration: FirCallableMemberDeclaration<F>) {
        visitElement(callableMemberDeclaration, null)
    }

    open fun visitDeclarationWithBody(declarationWithBody: FirDeclarationWithBody) {
        visitElement(declarationWithBody, null)
    }

    open fun visitAnonymousInitializer(anonymousInitializer: FirAnonymousInitializer) {
        visitElement(anonymousInitializer, null)
    }

    open fun <F : FirFunction<F>> visitFunction(function: FirFunction<F>) {
        visitElement(function, null)
    }

    open fun visitAnonymousFunction(anonymousFunction: FirAnonymousFunction) {
        visitElement(anonymousFunction, null)
    }

    open fun <F : FirMemberFunction<F>> visitMemberFunction(memberFunction: FirMemberFunction<F>) {
        visitElement(memberFunction, null)
    }

    open fun visitConstructor(constructor: FirConstructor) {
        visitElement(constructor, null)
    }

    open fun visitNamedFunction(namedFunction: FirNamedFunction) {
        visitElement(namedFunction, null)
    }

    open fun <F : FirFunction<F>> visitModifiableFunction(modifiableFunction: FirModifiableFunction<F>) {
        visitElement(modifiableFunction, null)
    }

    open fun visitPropertyAccessor(propertyAccessor: FirPropertyAccessor) {
        visitElement(propertyAccessor, null)
    }

    open fun visitErrorDeclaration(errorDeclaration: FirErrorDeclaration) {
        visitElement(errorDeclaration, null)
    }

    open fun visitField(field: FirField) {
        visitElement(field, null)
    }

    open fun visitNamedDeclaration(namedDeclaration: FirNamedDeclaration) {
        visitElement(namedDeclaration, null)
    }

    open fun visitMemberDeclaration(memberDeclaration: FirMemberDeclaration) {
        visitElement(memberDeclaration, null)
    }

    open fun <F : FirClassLikeDeclaration<F>> visitClassLikeDeclaration(classLikeDeclaration: FirClassLikeDeclaration<F>) {
        visitElement(classLikeDeclaration, null)
    }

    open fun visitRegularClass(regularClass: FirRegularClass) {
        visitElement(regularClass, null)
    }

    open fun visitEnumEntry(enumEntry: FirEnumEntry) {
        visitElement(enumEntry, null)
    }

    open fun visitTypeAlias(typeAlias: FirTypeAlias) {
        visitElement(typeAlias, null)
    }

    open fun visitTypeParameter(typeParameter: FirTypeParameter) {
        visitElement(typeParameter, null)
    }

    open fun visitProperty(property: FirProperty) {
        visitElement(property, null)
    }

    open fun visitTypedDeclaration(typedDeclaration: FirTypedDeclaration) {
        visitElement(typedDeclaration, null)
    }

    open fun visitValueParameter(valueParameter: FirValueParameter) {
        visitElement(valueParameter, null)
    }

    open fun <F : FirVariable<F>> visitVariable(variable: FirVariable<F>) {
        visitElement(variable, null)
    }

    open fun visitDeclarationStatus(declarationStatus: FirDeclarationStatus) {
        visitElement(declarationStatus, null)
    }

    open fun visitResolvedDeclarationStatus(resolvedDeclarationStatus: FirResolvedDeclarationStatus) {
        visitElement(resolvedDeclarationStatus, null)
    }

    open fun visitImport(import: FirImport) {
        visitElement(import, null)
    }

    open fun visitResolvedImport(resolvedImport: FirResolvedImport) {
        visitElement(resolvedImport, null)
    }

    open fun visitLabel(label: FirLabel) {
        visitElement(label, null)
    }

    open fun visitPackageFragment(packageFragment: FirPackageFragment) {
        visitElement(packageFragment, null)
    }

    open fun visitFile(file: FirFile) {
        visitElement(file, null)
    }

    open fun visitReference(reference: FirReference) {
        visitElement(reference, null)
    }

    open fun visitControlFlowGraphReference(controlFlowGraphReference: FirControlFlowGraphReference) {
        visitElement(controlFlowGraphReference, null)
    }

    open fun visitNamedReference(namedReference: FirNamedReference) {
        visitElement(namedReference, null)
    }

    open fun visitResolvedCallableReference(resolvedCallableReference: FirResolvedCallableReference) {
        visitElement(resolvedCallableReference, null)
    }

    open fun visitBackingFieldReference(backingFieldReference: FirBackingFieldReference) {
        visitElement(backingFieldReference, null)
    }

    open fun visitDelegateFieldReference(delegateFieldReference: FirDelegateFieldReference) {
        visitElement(delegateFieldReference, null)
    }

    open fun visitSuperReference(superReference: FirSuperReference) {
        visitElement(superReference, null)
    }

    open fun visitThisReference(thisReference: FirThisReference) {
        visitElement(thisReference, null)
    }

    open fun visitStatement(statement: FirStatement) {
        visitElement(statement, null)
    }

    open fun visitAnnotatedStatement(annotatedStatement: FirAnnotatedStatement) {
        visitElement(annotatedStatement, null)
    }

    open fun visitExpression(expression: FirExpression) {
        visitElement(expression, null)
    }

    open fun visitCall(call: FirCall) {
        visitElement(call, null)
    }

    open fun visitArraySetCall(arraySetCall: FirArraySetCall) {
        visitElement(arraySetCall, null)
    }

    open fun visitCallWithArgumentList(callWithArgumentList: FirCallWithArgumentList) {
        visitElement(callWithArgumentList, null)
    }

    open fun visitAnnotationCall(annotationCall: FirAnnotationCall) {
        visitElement(annotationCall, null)
    }

    open fun visitDelegatedConstructorCall(delegatedConstructorCall: FirDelegatedConstructorCall) {
        visitElement(delegatedConstructorCall, null)
    }

    open fun visitOperatorCall(operatorCall: FirOperatorCall) {
        visitElement(operatorCall, null)
    }

    open fun visitOperationBasedCall(operationBasedCall: FirOperationBasedCall) {
        visitElement(operationBasedCall, null)
    }

    open fun visitTypeOperatorCall(typeOperatorCall: FirTypeOperatorCall) {
        visitElement(typeOperatorCall, null)
    }

    open fun visitStringConcatenationCall(stringConcatenationCall: FirStringConcatenationCall) {
        visitElement(stringConcatenationCall, null)
    }

    open fun visitUnknownTypeCallWithArgumentList(unknownTypeCallWithArgumentList: FirUnknownTypeCallWithArgumentList) {
        visitElement(unknownTypeCallWithArgumentList, null)
    }

    open fun visitArrayOfCall(arrayOfCall: FirArrayOfCall) {
        visitElement(arrayOfCall, null)
    }

    open fun visitFunctionCall(functionCall: FirFunctionCall) {
        visitElement(functionCall, null)
    }

    open fun visitComponentCall(componentCall: FirComponentCall) {
        visitElement(componentCall, null)
    }

    open fun visitGetClassCall(getClassCall: FirGetClassCall) {
        visitElement(getClassCall, null)
    }

    open fun visitUncheckedNotNullCast(uncheckedNotNullCast: FirUncheckedNotNullCast) {
        visitElement(uncheckedNotNullCast, null)
    }

    open fun <E : FirTargetElement> visitJump(jump: FirJump<E>) {
        visitElement(jump, null)
    }

    open fun visitLoopJump(loopJump: FirLoopJump) {
        visitElement(loopJump, null)
    }

    open fun visitBreakExpression(breakExpression: FirBreakExpression) {
        visitElement(breakExpression, null)
    }

    open fun visitContinueExpression(continueExpression: FirContinueExpression) {
        visitElement(continueExpression, null)
    }

    open fun visitReturnExpression(returnExpression: FirReturnExpression) {
        visitElement(returnExpression, null)
    }

    open fun visitThrowExpression(throwExpression: FirThrowExpression) {
        visitElement(throwExpression, null)
    }

    open fun visitUnknownTypeExpression(unknownTypeExpression: FirUnknownTypeExpression) {
        visitElement(unknownTypeExpression, null)
    }

    open fun visitBinaryLogicExpression(binaryLogicExpression: FirBinaryLogicExpression) {
        visitElement(binaryLogicExpression, null)
    }

    open fun visitBlock(block: FirBlock) {
        visitElement(block, null)
    }

    open fun visitCallLikeControlFlowExpression(callLikeControlFlowExpression: FirCallLikeControlFlowExpression) {
        visitElement(callLikeControlFlowExpression, null)
    }

    open fun visitTryExpression(tryExpression: FirTryExpression) {
        visitElement(tryExpression, null)
    }

    open fun visitWhenExpression(whenExpression: FirWhenExpression) {
        visitElement(whenExpression, null)
    }

    open fun visitClassReferenceExpression(classReferenceExpression: FirClassReferenceExpression) {
        visitElement(classReferenceExpression, null)
    }

    open fun <T> visitConstExpression(constExpression: FirConstExpression<T>) {
        visitElement(constExpression, null)
    }

    open fun visitErrorExpression(errorExpression: FirErrorExpression) {
        visitElement(errorExpression, null)
    }

    open fun visitQualifiedAccessExpression(qualifiedAccessExpression: FirQualifiedAccessExpression) {
        visitElement(qualifiedAccessExpression, null)
    }

    open fun visitCallableReferenceAccess(callableReferenceAccess: FirCallableReferenceAccess) {
        visitElement(callableReferenceAccess, null)
    }

    open fun visitExpressionWithSmartcast(expressionWithSmartcast: FirExpressionWithSmartcast) {
        visitElement(expressionWithSmartcast, null)
    }

    open fun visitResolvedQualifier(resolvedQualifier: FirResolvedQualifier) {
        visitElement(resolvedQualifier, null)
    }

    open fun visitWhenSubjectExpression(whenSubjectExpression: FirWhenSubjectExpression) {
        visitElement(whenSubjectExpression, null)
    }

    open fun visitWrappedExpression(wrappedExpression: FirWrappedExpression) {
        visitElement(wrappedExpression, null)
    }

    open fun visitWrappedArgumentExpression(wrappedArgumentExpression: FirWrappedArgumentExpression) {
        visitElement(wrappedArgumentExpression, null)
    }

    open fun visitLambdaArgumentExpression(lambdaArgumentExpression: FirLambdaArgumentExpression) {
        visitElement(lambdaArgumentExpression, null)
    }

    open fun visitNamedArgumentExpression(namedArgumentExpression: FirNamedArgumentExpression) {
        visitElement(namedArgumentExpression, null)
    }

    open fun visitSpreadArgumentExpression(spreadArgumentExpression: FirSpreadArgumentExpression) {
        visitElement(spreadArgumentExpression, null)
    }

    open fun visitWrappedDelegateExpression(wrappedDelegateExpression: FirWrappedDelegateExpression) {
        visitElement(wrappedDelegateExpression, null)
    }

    open fun visitClass(klass: FirClass) {
        visitElement(klass, null)
    }

    open fun visitAnonymousObject(anonymousObject: FirAnonymousObject) {
        visitElement(anonymousObject, null)
    }

    open fun visitModifiableClass(modifiableClass: FirModifiableClass) {
        visitElement(modifiableClass, null)
    }

    open fun visitErrorStatement(errorStatement: FirErrorStatement) {
        visitElement(errorStatement, null)
    }

    open fun visitLoop(loop: FirLoop) {
        visitElement(loop, null)
    }

    open fun visitDoWhileLoop(doWhileLoop: FirDoWhileLoop) {
        visitElement(doWhileLoop, null)
    }

    open fun visitWhileLoop(whileLoop: FirWhileLoop) {
        visitElement(whileLoop, null)
    }

    open fun visitResolvable(resolvable: FirResolvable) {
        visitElement(resolvable, null)
    }

    open fun visitQualifiedAccess(qualifiedAccess: FirQualifiedAccess) {
        visitElement(qualifiedAccess, null)
    }

    open fun visitAssignment(assignment: FirAssignment) {
        visitElement(assignment, null)
    }

    open fun visitVariableAssignment(variableAssignment: FirVariableAssignment) {
        visitElement(variableAssignment, null)
    }

    open fun <C : FirReference> visitModifiableQualifiedAccess(modifiableQualifiedAccess: FirModifiableQualifiedAccess<C>) {
        visitElement(modifiableQualifiedAccess, null)
    }

    open fun visitTargetElement(targetElement: FirTargetElement) {
        visitElement(targetElement, null)
    }

    open fun visitLabeledElement(labeledElement: FirLabeledElement) {
        visitElement(labeledElement, null)
    }

    open fun visitTypeProjection(typeProjection: FirTypeProjection) {
        visitElement(typeProjection, null)
    }

    open fun visitStarProjection(starProjection: FirStarProjection) {
        visitElement(starProjection, null)
    }

    open fun visitTypeProjectionWithVariance(typeProjectionWithVariance: FirTypeProjectionWithVariance) {
        visitElement(typeProjectionWithVariance, null)
    }

    open fun visitTypeRef(typeRef: FirTypeRef) {
        visitElement(typeRef, null)
    }

    open fun visitDelegatedTypeRef(delegatedTypeRef: FirDelegatedTypeRef) {
        visitElement(delegatedTypeRef, null)
    }

    open fun visitImplicitTypeRef(implicitTypeRef: FirImplicitTypeRef) {
        visitElement(implicitTypeRef, null)
    }

    open fun visitResolvedTypeRef(resolvedTypeRef: FirResolvedTypeRef) {
        visitElement(resolvedTypeRef, null)
    }

    open fun visitErrorTypeRef(errorTypeRef: FirErrorTypeRef) {
        visitElement(errorTypeRef, null)
    }

    open fun visitResolvedFunctionTypeRef(resolvedFunctionTypeRef: FirResolvedFunctionTypeRef) {
        visitElement(resolvedFunctionTypeRef, null)
    }

    open fun visitTypeRefWithNullability(typeRefWithNullability: FirTypeRefWithNullability) {
        visitElement(typeRefWithNullability, null)
    }

    open fun visitDynamicTypeRef(dynamicTypeRef: FirDynamicTypeRef) {
        visitElement(dynamicTypeRef, null)
    }

    open fun visitFunctionTypeRef(functionTypeRef: FirFunctionTypeRef) {
        visitElement(functionTypeRef, null)
    }

    open fun visitUserTypeRef(userTypeRef: FirUserTypeRef) {
        visitElement(userTypeRef, null)
    }

    open fun visitWhenBranch(whenBranch: FirWhenBranch) {
        visitElement(whenBranch, null)
    }

    final override fun visitAnnotatedStatement(annotatedStatement: FirAnnotatedStatement, data: Nothing?) {
        visitAnnotatedStatement(annotatedStatement)
    }

    final override fun visitAnnotationCall(annotationCall: FirAnnotationCall, data: Nothing?) {
        visitAnnotationCall(annotationCall)
    }

    final override fun visitAnonymousFunction(anonymousFunction: FirAnonymousFunction, data: Nothing?) {
        visitAnonymousFunction(anonymousFunction)
    }

    final override fun visitAnonymousInitializer(anonymousInitializer: FirAnonymousInitializer, data: Nothing?) {
        visitAnonymousInitializer(anonymousInitializer)
    }

    final override fun visitAnonymousObject(anonymousObject: FirAnonymousObject, data: Nothing?) {
        visitAnonymousObject(anonymousObject)
    }

    final override fun visitArrayOfCall(arrayOfCall: FirArrayOfCall, data: Nothing?) {
        visitArrayOfCall(arrayOfCall)
    }

    final override fun visitArraySetCall(arraySetCall: FirArraySetCall, data: Nothing?) {
        visitArraySetCall(arraySetCall)
    }

    final override fun visitAssignment(assignment: FirAssignment, data: Nothing?) {
        visitAssignment(assignment)
    }

    final override fun visitBackingFieldReference(backingFieldReference: FirBackingFieldReference, data: Nothing?) {
        visitBackingFieldReference(backingFieldReference)
    }

    final override fun visitBinaryLogicExpression(binaryLogicExpression: FirBinaryLogicExpression, data: Nothing?) {
        visitBinaryLogicExpression(binaryLogicExpression)
    }

    final override fun visitBlock(block: FirBlock, data: Nothing?) {
        visitBlock(block)
    }

    final override fun visitBreakExpression(breakExpression: FirBreakExpression, data: Nothing?) {
        visitBreakExpression(breakExpression)
    }

    final override fun visitCall(call: FirCall, data: Nothing?) {
        visitCall(call)
    }

    final override fun visitCallLikeControlFlowExpression(callLikeControlFlowExpression: FirCallLikeControlFlowExpression, data: Nothing?) {
        visitCallLikeControlFlowExpression(callLikeControlFlowExpression)
    }

    final override fun visitCallWithArgumentList(callWithArgumentList: FirCallWithArgumentList, data: Nothing?) {
        visitCallWithArgumentList(callWithArgumentList)
    }

    final override fun <F : FirCallableDeclaration<F>> visitCallableDeclaration(callableDeclaration: FirCallableDeclaration<F>, data: Nothing?) {
        visitCallableDeclaration(callableDeclaration)
    }

    final override fun <F : FirCallableMemberDeclaration<F>> visitCallableMemberDeclaration(callableMemberDeclaration: FirCallableMemberDeclaration<F>, data: Nothing?) {
        visitCallableMemberDeclaration(callableMemberDeclaration)
    }

    final override fun visitCallableReferenceAccess(callableReferenceAccess: FirCallableReferenceAccess, data: Nothing?) {
        visitCallableReferenceAccess(callableReferenceAccess)
    }

    final override fun visitCatch(catch: FirCatch, data: Nothing?) {
        visitCatch(catch)
    }

    final override fun visitClass(klass: FirClass, data: Nothing?) {
        visitClass(klass)
    }

    final override fun <F : FirClassLikeDeclaration<F>> visitClassLikeDeclaration(classLikeDeclaration: FirClassLikeDeclaration<F>, data: Nothing?) {
        visitClassLikeDeclaration(classLikeDeclaration)
    }

    final override fun visitClassReferenceExpression(classReferenceExpression: FirClassReferenceExpression, data: Nothing?) {
        visitClassReferenceExpression(classReferenceExpression)
    }

    final override fun visitComponentCall(componentCall: FirComponentCall, data: Nothing?) {
        visitComponentCall(componentCall)
    }

    final override fun <T> visitConstExpression(constExpression: FirConstExpression<T>, data: Nothing?) {
        visitConstExpression(constExpression)
    }

    final override fun visitConstructor(constructor: FirConstructor, data: Nothing?) {
        visitConstructor(constructor)
    }

    final override fun visitContinueExpression(continueExpression: FirContinueExpression, data: Nothing?) {
        visitContinueExpression(continueExpression)
    }

    final override fun visitControlFlowGraphReference(controlFlowGraphReference: FirControlFlowGraphReference, data: Nothing?) {
        visitControlFlowGraphReference(controlFlowGraphReference)
    }

    final override fun visitDeclaration(declaration: FirDeclaration, data: Nothing?) {
        visitDeclaration(declaration)
    }

    final override fun visitDeclarationStatus(declarationStatus: FirDeclarationStatus, data: Nothing?) {
        visitDeclarationStatus(declarationStatus)
    }

    final override fun visitDeclarationWithBody(declarationWithBody: FirDeclarationWithBody, data: Nothing?) {
        visitDeclarationWithBody(declarationWithBody)
    }

    final override fun visitDelegateFieldReference(delegateFieldReference: FirDelegateFieldReference, data: Nothing?) {
        visitDelegateFieldReference(delegateFieldReference)
    }

    final override fun visitDelegatedConstructorCall(delegatedConstructorCall: FirDelegatedConstructorCall, data: Nothing?) {
        visitDelegatedConstructorCall(delegatedConstructorCall)
    }

    final override fun visitDelegatedTypeRef(delegatedTypeRef: FirDelegatedTypeRef, data: Nothing?) {
        visitDelegatedTypeRef(delegatedTypeRef)
    }

    final override fun visitDoWhileLoop(doWhileLoop: FirDoWhileLoop, data: Nothing?) {
        visitDoWhileLoop(doWhileLoop)
    }

    final override fun visitDynamicTypeRef(dynamicTypeRef: FirDynamicTypeRef, data: Nothing?) {
        visitDynamicTypeRef(dynamicTypeRef)
    }

    final override fun visitEnumEntry(enumEntry: FirEnumEntry, data: Nothing?) {
        visitEnumEntry(enumEntry)
    }

    final override fun visitErrorDeclaration(errorDeclaration: FirErrorDeclaration, data: Nothing?) {
        visitErrorDeclaration(errorDeclaration)
    }

    final override fun visitErrorExpression(errorExpression: FirErrorExpression, data: Nothing?) {
        visitErrorExpression(errorExpression)
    }

    final override fun visitErrorStatement(errorStatement: FirErrorStatement, data: Nothing?) {
        visitErrorStatement(errorStatement)
    }

    final override fun visitErrorTypeRef(errorTypeRef: FirErrorTypeRef, data: Nothing?) {
        visitErrorTypeRef(errorTypeRef)
    }

    final override fun visitExpression(expression: FirExpression, data: Nothing?) {
        visitExpression(expression)
    }

    final override fun visitExpressionWithSmartcast(expressionWithSmartcast: FirExpressionWithSmartcast, data: Nothing?) {
        visitExpressionWithSmartcast(expressionWithSmartcast)
    }

    final override fun visitField(field: FirField, data: Nothing?) {
        visitField(field)
    }

    final override fun visitFile(file: FirFile, data: Nothing?) {
        visitFile(file)
    }

    final override fun <F : FirFunction<F>> visitFunction(function: FirFunction<F>, data: Nothing?) {
        visitFunction(function)
    }

    final override fun visitFunctionCall(functionCall: FirFunctionCall, data: Nothing?) {
        visitFunctionCall(functionCall)
    }

    final override fun visitFunctionTypeRef(functionTypeRef: FirFunctionTypeRef, data: Nothing?) {
        visitFunctionTypeRef(functionTypeRef)
    }

    final override fun visitGetClassCall(getClassCall: FirGetClassCall, data: Nothing?) {
        visitGetClassCall(getClassCall)
    }

    final override fun visitImplicitTypeRef(implicitTypeRef: FirImplicitTypeRef, data: Nothing?) {
        visitImplicitTypeRef(implicitTypeRef)
    }

    final override fun visitImport(import: FirImport, data: Nothing?) {
        visitImport(import)
    }

    final override fun <E : FirTargetElement> visitJump(jump: FirJump<E>, data: Nothing?) {
        visitJump(jump)
    }

    final override fun visitLabel(label: FirLabel, data: Nothing?) {
        visitLabel(label)
    }

    final override fun visitLabeledElement(labeledElement: FirLabeledElement, data: Nothing?) {
        visitLabeledElement(labeledElement)
    }

    final override fun visitLambdaArgumentExpression(lambdaArgumentExpression: FirLambdaArgumentExpression, data: Nothing?) {
        visitLambdaArgumentExpression(lambdaArgumentExpression)
    }

    final override fun visitLoop(loop: FirLoop, data: Nothing?) {
        visitLoop(loop)
    }

    final override fun visitLoopJump(loopJump: FirLoopJump, data: Nothing?) {
        visitLoopJump(loopJump)
    }

    final override fun visitMemberDeclaration(memberDeclaration: FirMemberDeclaration, data: Nothing?) {
        visitMemberDeclaration(memberDeclaration)
    }

    final override fun <F : FirMemberFunction<F>> visitMemberFunction(memberFunction: FirMemberFunction<F>, data: Nothing?) {
        visitMemberFunction(memberFunction)
    }

    final override fun visitModifiableClass(modifiableClass: FirModifiableClass, data: Nothing?) {
        visitModifiableClass(modifiableClass)
    }

    final override fun <F : FirFunction<F>> visitModifiableFunction(modifiableFunction: FirModifiableFunction<F>, data: Nothing?) {
        visitModifiableFunction(modifiableFunction)
    }

    final override fun <C : FirReference> visitModifiableQualifiedAccess(modifiableQualifiedAccess: FirModifiableQualifiedAccess<C>, data: Nothing?) {
        visitModifiableQualifiedAccess(modifiableQualifiedAccess)
    }

    final override fun visitNamedArgumentExpression(namedArgumentExpression: FirNamedArgumentExpression, data: Nothing?) {
        visitNamedArgumentExpression(namedArgumentExpression)
    }

    final override fun visitNamedDeclaration(namedDeclaration: FirNamedDeclaration, data: Nothing?) {
        visitNamedDeclaration(namedDeclaration)
    }

    final override fun visitNamedFunction(namedFunction: FirNamedFunction, data: Nothing?) {
        visitNamedFunction(namedFunction)
    }

    final override fun visitNamedReference(namedReference: FirNamedReference, data: Nothing?) {
        visitNamedReference(namedReference)
    }

    final override fun visitOperationBasedCall(operationBasedCall: FirOperationBasedCall, data: Nothing?) {
        visitOperationBasedCall(operationBasedCall)
    }

    final override fun visitOperatorCall(operatorCall: FirOperatorCall, data: Nothing?) {
        visitOperatorCall(operatorCall)
    }

    final override fun visitPackageFragment(packageFragment: FirPackageFragment, data: Nothing?) {
        visitPackageFragment(packageFragment)
    }

    final override fun visitProperty(property: FirProperty, data: Nothing?) {
        visitProperty(property)
    }

    final override fun visitPropertyAccessor(propertyAccessor: FirPropertyAccessor, data: Nothing?) {
        visitPropertyAccessor(propertyAccessor)
    }

    final override fun visitQualifiedAccess(qualifiedAccess: FirQualifiedAccess, data: Nothing?) {
        visitQualifiedAccess(qualifiedAccess)
    }

    final override fun visitQualifiedAccessExpression(qualifiedAccessExpression: FirQualifiedAccessExpression, data: Nothing?) {
        visitQualifiedAccessExpression(qualifiedAccessExpression)
    }

    final override fun visitReference(reference: FirReference, data: Nothing?) {
        visitReference(reference)
    }

    final override fun visitRegularClass(regularClass: FirRegularClass, data: Nothing?) {
        visitRegularClass(regularClass)
    }

    final override fun visitResolvable(resolvable: FirResolvable, data: Nothing?) {
        visitResolvable(resolvable)
    }

    final override fun visitResolvedCallableReference(resolvedCallableReference: FirResolvedCallableReference, data: Nothing?) {
        visitResolvedCallableReference(resolvedCallableReference)
    }

    final override fun visitResolvedDeclarationStatus(resolvedDeclarationStatus: FirResolvedDeclarationStatus, data: Nothing?) {
        visitResolvedDeclarationStatus(resolvedDeclarationStatus)
    }

    final override fun visitResolvedFunctionTypeRef(resolvedFunctionTypeRef: FirResolvedFunctionTypeRef, data: Nothing?) {
        visitResolvedFunctionTypeRef(resolvedFunctionTypeRef)
    }

    final override fun visitResolvedImport(resolvedImport: FirResolvedImport, data: Nothing?) {
        visitResolvedImport(resolvedImport)
    }

    final override fun visitResolvedQualifier(resolvedQualifier: FirResolvedQualifier, data: Nothing?) {
        visitResolvedQualifier(resolvedQualifier)
    }

    final override fun visitResolvedTypeRef(resolvedTypeRef: FirResolvedTypeRef, data: Nothing?) {
        visitResolvedTypeRef(resolvedTypeRef)
    }

    final override fun visitReturnExpression(returnExpression: FirReturnExpression, data: Nothing?) {
        visitReturnExpression(returnExpression)
    }

    final override fun visitSpreadArgumentExpression(spreadArgumentExpression: FirSpreadArgumentExpression, data: Nothing?) {
        visitSpreadArgumentExpression(spreadArgumentExpression)
    }

    final override fun visitStarProjection(starProjection: FirStarProjection, data: Nothing?) {
        visitStarProjection(starProjection)
    }

    final override fun visitStatement(statement: FirStatement, data: Nothing?) {
        visitStatement(statement)
    }

    final override fun visitStringConcatenationCall(stringConcatenationCall: FirStringConcatenationCall, data: Nothing?) {
        visitStringConcatenationCall(stringConcatenationCall)
    }

    final override fun visitSuperReference(superReference: FirSuperReference, data: Nothing?) {
        visitSuperReference(superReference)
    }

    final override fun visitTargetElement(targetElement: FirTargetElement, data: Nothing?) {
        visitTargetElement(targetElement)
    }

    final override fun visitThisReference(thisReference: FirThisReference, data: Nothing?) {
        visitThisReference(thisReference)
    }

    final override fun visitThrowExpression(throwExpression: FirThrowExpression, data: Nothing?) {
        visitThrowExpression(throwExpression)
    }

    final override fun visitTryExpression(tryExpression: FirTryExpression, data: Nothing?) {
        visitTryExpression(tryExpression)
    }

    final override fun visitTypeAlias(typeAlias: FirTypeAlias, data: Nothing?) {
        visitTypeAlias(typeAlias)
    }

    final override fun visitTypeOperatorCall(typeOperatorCall: FirTypeOperatorCall, data: Nothing?) {
        visitTypeOperatorCall(typeOperatorCall)
    }

    final override fun visitTypeParameter(typeParameter: FirTypeParameter, data: Nothing?) {
        visitTypeParameter(typeParameter)
    }

    final override fun visitTypeProjection(typeProjection: FirTypeProjection, data: Nothing?) {
        visitTypeProjection(typeProjection)
    }

    final override fun visitTypeProjectionWithVariance(typeProjectionWithVariance: FirTypeProjectionWithVariance, data: Nothing?) {
        visitTypeProjectionWithVariance(typeProjectionWithVariance)
    }

    final override fun visitTypeRef(typeRef: FirTypeRef, data: Nothing?) {
        visitTypeRef(typeRef)
    }

    final override fun visitTypeRefWithNullability(typeRefWithNullability: FirTypeRefWithNullability, data: Nothing?) {
        visitTypeRefWithNullability(typeRefWithNullability)
    }

    final override fun visitTypedDeclaration(typedDeclaration: FirTypedDeclaration, data: Nothing?) {
        visitTypedDeclaration(typedDeclaration)
    }

    final override fun visitUncheckedNotNullCast(uncheckedNotNullCast: FirUncheckedNotNullCast, data: Nothing?) {
        visitUncheckedNotNullCast(uncheckedNotNullCast)
    }

    final override fun visitUnknownTypeCallWithArgumentList(unknownTypeCallWithArgumentList: FirUnknownTypeCallWithArgumentList, data: Nothing?) {
        visitUnknownTypeCallWithArgumentList(unknownTypeCallWithArgumentList)
    }

    final override fun visitUnknownTypeExpression(unknownTypeExpression: FirUnknownTypeExpression, data: Nothing?) {
        visitUnknownTypeExpression(unknownTypeExpression)
    }

    final override fun visitUserTypeRef(userTypeRef: FirUserTypeRef, data: Nothing?) {
        visitUserTypeRef(userTypeRef)
    }

    final override fun visitValueParameter(valueParameter: FirValueParameter, data: Nothing?) {
        visitValueParameter(valueParameter)
    }

    final override fun <F : FirVariable<F>> visitVariable(variable: FirVariable<F>, data: Nothing?) {
        visitVariable(variable)
    }

    final override fun visitVariableAssignment(variableAssignment: FirVariableAssignment, data: Nothing?) {
        visitVariableAssignment(variableAssignment)
    }

    final override fun visitWhenBranch(whenBranch: FirWhenBranch, data: Nothing?) {
        visitWhenBranch(whenBranch)
    }

    final override fun visitWhenExpression(whenExpression: FirWhenExpression, data: Nothing?) {
        visitWhenExpression(whenExpression)
    }

    final override fun visitWhenSubjectExpression(whenSubjectExpression: FirWhenSubjectExpression, data: Nothing?) {
        visitWhenSubjectExpression(whenSubjectExpression)
    }

    final override fun visitWhileLoop(whileLoop: FirWhileLoop, data: Nothing?) {
        visitWhileLoop(whileLoop)
    }

    final override fun visitWrappedArgumentExpression(wrappedArgumentExpression: FirWrappedArgumentExpression, data: Nothing?) {
        visitWrappedArgumentExpression(wrappedArgumentExpression)
    }

    final override fun visitWrappedDelegateExpression(wrappedDelegateExpression: FirWrappedDelegateExpression, data: Nothing?) {
        visitWrappedDelegateExpression(wrappedDelegateExpression)
    }

    final override fun visitWrappedExpression(wrappedExpression: FirWrappedExpression, data: Nothing?) {
        visitWrappedExpression(wrappedExpression)
    }

    final override fun visitElement(element: FirElement, data: Nothing?) {
        visitElement(element)
    }

}
