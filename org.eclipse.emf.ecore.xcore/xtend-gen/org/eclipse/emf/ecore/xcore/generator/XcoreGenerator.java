package org.eclipse.emf.ecore.xcore.generator;

import com.google.inject.Inject;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.util.MappingFacade;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.compiler.StringBuilderBasedAppendable;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.EObjectExtensions;

@SuppressWarnings("all")
public class XcoreGenerator implements IGenerator {
  
  @Inject
  private MappingFacade mappings;
  
  @Inject
  private XbaseCompiler compiler;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    {
      EList<EObject> _contents = resource.getContents();
      EObject _head = IterableExtensions.<EObject>head(_contents);
      final XPackage pack = ((XPackage) _head);
      Iterable<EObject> _allContentsIterable = EObjectExtensions.allContentsIterable(pack);
      Iterable<XOperation> _filter = IterableExtensions.<XOperation>filter(_allContentsIterable, org.eclipse.emf.ecore.xcore.XOperation.class);
      for (XOperation op : _filter) {
        {
          EOperation _eOperation = this.mappings.getEOperation(op);
          final EOperation eOperation = _eOperation;
          StringBuilderBasedAppendable _stringBuilderBasedAppendable = new StringBuilderBasedAppendable();
          final StringBuilderBasedAppendable appendable = _stringBuilderBasedAppendable;
          JvmOperation _jvmOperation = this.mappings.getJvmOperation(op);
          JvmTypeReference _returnType = _jvmOperation.getReturnType();
          final JvmTypeReference expectedType = _returnType;
          XBlockExpression _body = op.getBody();
          this.compiler.compile(_body, appendable, expectedType);
          EList<EAnnotation> _eAnnotations = eOperation.getEAnnotations();
          String _string = appendable.toString();
          EAnnotation _createGenModelAnnotation = this.createGenModelAnnotation("body", _string);
          _eAnnotations.add(_createGenModelAnnotation);
        }
      }
      EList<EObject> _contents_1 = resource.getContents();
      EObject _get = _contents_1.get(2);
      this.generateGenModel(((GenModel) _get));
    }
  }
  
  public void generateGenModel(final GenModel genModel) {
    genModel.gen(null);
  }
  
  public EAnnotation createGenModelAnnotation(final String key, final String value) {
    {
      EAnnotation _createEAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      final EAnnotation result = _createEAnnotation;
      result.setSource(GenModelPackage.eNS_URI);
      EMap<String,String> _details = result.getDetails();
      _details.put(key, value);
      return result;
    }
  }
}