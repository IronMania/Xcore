/*
 * generated by Xtext
 */
package org.eclipse.emf.ecore.xcore.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.xcore.XPackage
import com.google.inject.Inject
import org.eclipse.emf.ecore.xcore.util.MappingFacade
import org.eclipse.emf.ecore.xcore.XOperation
import static extension org.eclipse.xtext.xtend2.lib.EObjectExtensions.*
import org.eclipse.xtext.xbase.compiler.XbaseCompiler
import org.eclipse.xtext.xbase.compiler.IAppendable
import org.eclipse.xtext.xbase.compiler.StringBuilderBasedAppendable
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenModel

class XcoreGenerator implements IGenerator {
	
	@Inject
	extension MappingFacade mappings
	
	@Inject
	XbaseCompiler compiler
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		val pack = resource.contents.head as XPackage
		// install operation bodies
		for (op : pack.allContentsIterable.filter(typeof(XOperation))) {
			val eOperation = op.EOperation
			val appendable = new StringBuilderBasedAppendable()
			val expectedType = op.jvmOperation.returnType
			compiler.compile(op.body, appendable, expectedType)
			eOperation.EAnnotations.add(createGenModelAnnotation("body", appendable.toString))
		}
		
		generateGenModel(resource.contents.get(2) as GenModel)
	}
	
	def generateGenModel(GenModel genModel) {
		genModel.gen(null)
	}
	
	def createGenModelAnnotation(String key, String value) {
		val result = EcoreFactory::eINSTANCE.createEAnnotation
		result.source = GenModelPackage::eNS_URI
		result.details.put(key, value)
		return result
	}
}