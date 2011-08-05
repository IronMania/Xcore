package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmOperation;

public class XFeatureMapping extends AbstractMapping
{
	private GenFeature genFeature;
	private JvmField field;
	private JvmOperation getter;
	private JvmOperation setter;
	private EStructuralFeature eStructuralFeature;
	
	public GenFeature getGenFeature()
  {
  	return genFeature;
  }
	public void setGenFeature(GenFeature genFeature)
  {
  	this.genFeature = genFeature;
  }
	public JvmField getField()
  {
  	return field;
  }
	public void setField(JvmField field)
  {
  	this.field = field;
  }
	public JvmOperation getGetter()
  {
  	return getter;
  }
	public void setGetter(JvmOperation getter)
  {
  	this.getter = getter;
  }
	public JvmOperation getSetter()
  {
  	return setter;
  }
	public void setSetter(JvmOperation setter)
  {
  	this.setter = setter;
  }
	
	public EStructuralFeature getEStructuralFeature()
  {
	  return eStructuralFeature;
  }
	
	public void setEStructuralFeature(EStructuralFeature eStructuralFeature)
  {
	  this.eStructuralFeature = eStructuralFeature;
  }
}
