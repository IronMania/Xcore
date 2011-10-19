package org.eclipse.emf.ecore.xcore.tests.scoping;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class LinkingTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Test
  public void linkVoidReturnType() throws Exception {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val int i = 0");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      XVariableDeclaration _firstVariableDeclaration = this.firstVariableDeclaration(pack);
      final XVariableDeclaration declaration = _firstVariableDeclaration;
      JvmTypeReference _type = declaration.getType();
      JvmType _type_1 = _type.getType();
      final JvmType intType = _type_1;
      boolean _eIsProxy = intType.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      String _qualifiedName = intType.getQualifiedName();
      Assert.assertEquals("int", _qualifiedName);
  }
  
  @Test
  public void linkQualifiedStringType() throws Exception {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val java.lang.String s = null");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      XVariableDeclaration _firstVariableDeclaration = this.firstVariableDeclaration(pack);
      final XVariableDeclaration declaration = _firstVariableDeclaration;
      JvmTypeReference _type = declaration.getType();
      JvmType _type_1 = _type.getType();
      final JvmType stringType = _type_1;
      boolean _eIsProxy = stringType.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      String _qualifiedName = stringType.getQualifiedName();
      Assert.assertEquals("java.lang.String", _qualifiedName);
  }
  
  @Test
  public void linkInternalDefinedType() throws Exception {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val Bar b = null");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      XVariableDeclaration _firstVariableDeclaration = this.firstVariableDeclaration(pack);
      final XVariableDeclaration declaration = _firstVariableDeclaration;
      JvmTypeReference _type = declaration.getType();
      JvmType _type_1 = _type.getType();
      final JvmType stringType = _type_1;
      boolean _eIsProxy = stringType.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      String _qualifiedName = stringType.getQualifiedName();
      Assert.assertEquals("foo.Bar", _qualifiedName);
  }
  
  @Test
  public void linkFeatureCallType() throws Exception {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val s = \'a\' + \'b\'");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      XVariableDeclaration _firstVariableDeclaration = this.firstVariableDeclaration(pack);
      final XVariableDeclaration declaration = _firstVariableDeclaration;
      XExpression _right = declaration.getRight();
      final XBinaryOperation binaryOperation = ((XBinaryOperation) _right);
      JvmIdentifiableElement _feature = binaryOperation.getFeature();
      final JvmIdentifiableElement feature = _feature;
      boolean _eIsProxy = feature.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      String _simpleName = feature.getSimpleName();
      Assert.assertEquals("operator_plus", _simpleName);
  }
  
  public XVariableDeclaration firstVariableDeclaration(final XPackage pack) {
    XVariableDeclaration _xblockexpression = null;
    {
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _head = IterableExtensions.<XClassifier>head(_classifiers);
      final XClass clazz = ((XClass) _head);
      EList<XMember> _members = clazz.getMembers();
      XMember _head_1 = IterableExtensions.<XMember>head(_members);
      final XOperation operation = ((XOperation) _head_1);
      XBlockExpression _body = operation.getBody();
      final XBlockExpression block = ((XBlockExpression) _body);
      EList<XExpression> _expressions = block.getExpressions();
      XExpression _head_2 = IterableExtensions.<XExpression>head(_expressions);
      final XVariableDeclaration declaration = ((XVariableDeclaration) _head_2);
      _xblockexpression = (declaration);
    }
    return _xblockexpression;
  }
}
