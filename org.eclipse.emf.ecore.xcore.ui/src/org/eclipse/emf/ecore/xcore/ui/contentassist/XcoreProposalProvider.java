/*
* generated by Xtext
*/
package org.eclipse.emf.ecore.xcore.ui.contentassist;


import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.ui.contentassist.AbstractXcoreProposalProvider;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal.IReplacementTextApplier;

import com.google.common.base.Predicate;
import com.google.inject.Inject;


/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
public class XcoreProposalProvider extends AbstractXcoreProposalProvider
{
  @Inject
  IScopeProvider xcoreScopeProvider;

  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  @Inject
  QualifiedNameValueConverter qualifiedNameValueConverter;
  
  @Inject
  private XcoreMapper mapper;

  @Override
  public void completeXReference_Opposite
    (EObject model, 
     Assignment assignment, 
     ContentAssistContext context, 
     ICompletionProposalAcceptor acceptor) 
  {
    final IReplacementTextApplier textApplier = 
        new OppositeReplacementTextApplier
          ((XReference)model,
           context.getViewer(),
           xcoreScopeProvider.getScope(model, XcorePackage.Literals.XREFERENCE__OPPOSITE),
           mapper,
           qualifiedNameConverter,
           qualifiedNameValueConverter);
    final ICompletionProposalAcceptor oppositeAware = new ICompletionProposalAcceptor.Delegate(acceptor)
      {
        @Override
        public void accept(ICompletionProposal proposal)
        {
          if (proposal instanceof ConfigurableCompletionProposal && textApplier != null)
          {
            ((ConfigurableCompletionProposal)proposal).setTextApplier(textApplier);
          }
          super.accept(proposal);
        }
      };
    super.completeXReference_Opposite(model, assignment, context, oppositeAware);
  }

  @Override
  public void completeXGenericType_Type(
    EObject model,
    Assignment assignment,
    ContentAssistContext context,
    ICompletionProposalAcceptor acceptor)
  {
    final IReplacementTextApplier textApplier = new ImportingTypesProposalProvider.FQNImporter(
      context.getResource(),
      context.getViewer(),
      xcoreScopeProvider.getScope(model, XcorePackage.Literals.XGENERIC_TYPE__TYPE),
      qualifiedNameConverter,
      qualifiedNameValueConverter,
      qualifiedNameValueConverter);
    final ICompletionProposalAcceptor scopeAware = new ICompletionProposalAcceptor.Delegate(acceptor)
      {
        @Override
        public void accept(ICompletionProposal proposal)
        {
          if (proposal instanceof ConfigurableCompletionProposal && textApplier != null)
          {
            ((ConfigurableCompletionProposal)proposal).setTextApplier(textApplier);
          }
          super.accept(proposal);
        }
      };
    super.completeXGenericType_Type(model, assignment, context, scopeAware);
  }
  
  @Override
  protected void lookupCrossReference(CrossReference crossReference, EReference reference,
    ContentAssistContext contentAssistContext, ICompletionProposalAcceptor acceptor,
    Predicate<IEObjectDescription> filter)
  {
    if (reference == XcorePackage.Literals.XREFERENCE__OPPOSITE)
    {
      XReference xReference = (XReference)contentAssistContext.getCurrentModel();
      final EStructuralFeature eReference = mapper.getMapping(xReference).getEStructuralFeature();
      final EClass eClass = eReference.getEContainingClass();
      final Predicate<IEObjectDescription> baseFilter = filter;
      filter =
        new Predicate<IEObjectDescription>()
        {
          public boolean apply(IEObjectDescription input) 
          { 
            // Filter out features that aren't of the correct type to be a valid opposite.
            //
            GenFeature genFeature = (GenFeature)input.getEObjectOrProxy();
            EStructuralFeature eStructuralFeature = genFeature.getEcoreFeature();
            return eStructuralFeature.getEType() == eClass && eStructuralFeature != eReference && baseFilter.apply(input);
          }
        };
    }
    super.lookupCrossReference (crossReference, reference, contentAssistContext, acceptor, filter);
  }
}
