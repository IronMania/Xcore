package org.eclipse.emf.ecore.xcore.tests.scoping;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class XbaseScopingTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  private ValidationTestHelper validator;
  
  @Test
  public void testLinkToThis() throws Exception {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class X {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op X foo(X x) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return this.foo(x)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
  }
}
