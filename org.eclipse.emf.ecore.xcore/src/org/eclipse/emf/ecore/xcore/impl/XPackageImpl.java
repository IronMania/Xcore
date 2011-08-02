/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XPackage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XPackageImpl#getNsURI <em>Ns URI</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XPackageImpl#getNsPrefix <em>Ns Prefix</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XPackageImpl#getImportDirectives <em>Import Directives</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XPackageImpl#getAnnotationDirectives <em>Annotation Directives</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XPackageImpl#getClassifiers <em>Classifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XPackageImpl extends XNamedElementImpl implements XPackage
{
  /**
   * The default value of the '{@link #getNsURI() <em>Ns URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNsURI()
   * @generated
   * @ordered
   */
  protected static final String NS_URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNsURI() <em>Ns URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNsURI()
   * @generated
   * @ordered
   */
  protected String nsURI = NS_URI_EDEFAULT;

  /**
   * The default value of the '{@link #getNsPrefix() <em>Ns Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNsPrefix()
   * @generated
   * @ordered
   */
  protected static final String NS_PREFIX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNsPrefix() <em>Ns Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNsPrefix()
   * @generated
   * @ordered
   */
  protected String nsPrefix = NS_PREFIX_EDEFAULT;

  /**
   * The cached value of the '{@link #getImportDirectives() <em>Import Directives</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportDirectives()
   * @generated
   * @ordered
   */
  protected EList<XImportDirective> importDirectives;

  /**
   * The cached value of the '{@link #getAnnotationDirectives() <em>Annotation Directives</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotationDirectives()
   * @generated
   * @ordered
   */
  protected EList<XAnnotationDirective> annotationDirectives;

  /**
   * The cached value of the '{@link #getClassifiers() <em>Classifiers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassifiers()
   * @generated
   * @ordered
   */
  protected EList<XClassifier> classifiers;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XPackageImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XcorePackage.Literals.XPACKAGE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNsURI()
  {
    return nsURI;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNsURI(String newNsURI)
  {
    String oldNsURI = nsURI;
    nsURI = newNsURI;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XPACKAGE__NS_URI, oldNsURI, nsURI));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNsPrefix()
  {
    return nsPrefix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNsPrefix(String newNsPrefix)
  {
    String oldNsPrefix = nsPrefix;
    nsPrefix = newNsPrefix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XPACKAGE__NS_PREFIX, oldNsPrefix, nsPrefix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XImportDirective> getImportDirectives()
  {
    if (importDirectives == null)
    {
      importDirectives = new EObjectContainmentEList<XImportDirective>(XImportDirective.class, this, XcorePackage.XPACKAGE__IMPORT_DIRECTIVES);
    }
    return importDirectives;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XAnnotationDirective> getAnnotationDirectives()
  {
    if (annotationDirectives == null)
    {
      annotationDirectives = new EObjectContainmentEList<XAnnotationDirective>(XAnnotationDirective.class, this, XcorePackage.XPACKAGE__ANNOTATION_DIRECTIVES);
    }
    return annotationDirectives;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XClassifier> getClassifiers()
  {
    if (classifiers == null)
    {
      classifiers = new EObjectContainmentWithInverseEList<XClassifier>(XClassifier.class, this, XcorePackage.XPACKAGE__CLASSIFIERS, XcorePackage.XCLASSIFIER__PACKAGE);
    }
    return classifiers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XcorePackage.XPACKAGE__CLASSIFIERS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getClassifiers()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XcorePackage.XPACKAGE__IMPORT_DIRECTIVES:
        return ((InternalEList<?>)getImportDirectives()).basicRemove(otherEnd, msgs);
      case XcorePackage.XPACKAGE__ANNOTATION_DIRECTIVES:
        return ((InternalEList<?>)getAnnotationDirectives()).basicRemove(otherEnd, msgs);
      case XcorePackage.XPACKAGE__CLASSIFIERS:
        return ((InternalEList<?>)getClassifiers()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XcorePackage.XPACKAGE__NS_URI:
        return getNsURI();
      case XcorePackage.XPACKAGE__NS_PREFIX:
        return getNsPrefix();
      case XcorePackage.XPACKAGE__IMPORT_DIRECTIVES:
        return getImportDirectives();
      case XcorePackage.XPACKAGE__ANNOTATION_DIRECTIVES:
        return getAnnotationDirectives();
      case XcorePackage.XPACKAGE__CLASSIFIERS:
        return getClassifiers();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XcorePackage.XPACKAGE__NS_URI:
        setNsURI((String)newValue);
        return;
      case XcorePackage.XPACKAGE__NS_PREFIX:
        setNsPrefix((String)newValue);
        return;
      case XcorePackage.XPACKAGE__IMPORT_DIRECTIVES:
        getImportDirectives().clear();
        getImportDirectives().addAll((Collection<? extends XImportDirective>)newValue);
        return;
      case XcorePackage.XPACKAGE__ANNOTATION_DIRECTIVES:
        getAnnotationDirectives().clear();
        getAnnotationDirectives().addAll((Collection<? extends XAnnotationDirective>)newValue);
        return;
      case XcorePackage.XPACKAGE__CLASSIFIERS:
        getClassifiers().clear();
        getClassifiers().addAll((Collection<? extends XClassifier>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case XcorePackage.XPACKAGE__NS_URI:
        setNsURI(NS_URI_EDEFAULT);
        return;
      case XcorePackage.XPACKAGE__NS_PREFIX:
        setNsPrefix(NS_PREFIX_EDEFAULT);
        return;
      case XcorePackage.XPACKAGE__IMPORT_DIRECTIVES:
        getImportDirectives().clear();
        return;
      case XcorePackage.XPACKAGE__ANNOTATION_DIRECTIVES:
        getAnnotationDirectives().clear();
        return;
      case XcorePackage.XPACKAGE__CLASSIFIERS:
        getClassifiers().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case XcorePackage.XPACKAGE__NS_URI:
        return NS_URI_EDEFAULT == null ? nsURI != null : !NS_URI_EDEFAULT.equals(nsURI);
      case XcorePackage.XPACKAGE__NS_PREFIX:
        return NS_PREFIX_EDEFAULT == null ? nsPrefix != null : !NS_PREFIX_EDEFAULT.equals(nsPrefix);
      case XcorePackage.XPACKAGE__IMPORT_DIRECTIVES:
        return importDirectives != null && !importDirectives.isEmpty();
      case XcorePackage.XPACKAGE__ANNOTATION_DIRECTIVES:
        return annotationDirectives != null && !annotationDirectives.isEmpty();
      case XcorePackage.XPACKAGE__CLASSIFIERS:
        return classifiers != null && !classifiers.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (nsURI: ");
    result.append(nsURI);
    result.append(", nsPrefix: ");
    result.append(nsPrefix);
    result.append(')');
    return result.toString();
  }

} //XPackageImpl
