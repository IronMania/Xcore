package org.eclipse.emf.ecore.xcore.generator;

import java.util.Collection;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.xtext.generator.IFileSystemAccess;

import com.google.inject.Inject;
import com.google.inject.Provider;

import static java.util.Collections.*;

public class XcoreGeneratorImpl extends Generator
{
	
	static final String OUTPUT_DIR_MARKER = "special_model_dir";
	private IFileSystemAccess fsa;

	public void setFileSystemAccess(IFileSystemAccess fsa)
	{
		this.fsa = fsa;
	}
	
	@Override
	public void setInput(Object input)
	{
		if (input instanceof GenModel) {
			final GenModel genModel = (GenModel) input;
			genModel.setModelDirectory(OUTPUT_DIR_MARKER);
			genModel.unsetEditDirectory();
			genModel.unsetEditPluginClass();
			genModel.unsetEditorDirectory();
			genModel.unsetEditorPluginClass();
			genModel.unsetTestsDirectory();
			genModel.unsetTestSuiteClass();
		}
	  super.setInput(input);
	}
	
	@Inject
	private Provider<XcoreGenModelGeneratorAdapterFactory> adapterFactoryProvider;

	@Override
	protected Collection<GeneratorAdapterFactory> getAdapterFactories(Object object)
	{
		final XcoreGenModelGeneratorAdapterFactory genAdapterFactory = adapterFactoryProvider.get();
		genAdapterFactory.setGenerator(this);
		genAdapterFactory.setFileSystemAccess(fsa);
		return singleton((GeneratorAdapterFactory) genAdapterFactory);
	}
}
