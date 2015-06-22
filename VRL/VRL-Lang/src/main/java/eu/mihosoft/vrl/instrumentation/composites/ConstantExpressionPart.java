package eu.mihosoft.vrl.instrumentation.composites;

import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.control.SourceUnit;

import eu.mihosoft.vrl.instrumentation.StateMachine;
import eu.mihosoft.vrl.instrumentation.transform.TransformContext;
import eu.mihosoft.vrl.lang.model.Argument;
import eu.mihosoft.vrl.lang.model.CodeEntity;
import eu.mihosoft.vrl.lang.model.CodeLineColumnMapper;
import eu.mihosoft.vrl.lang.model.ControlFlowScope;
import eu.mihosoft.vrl.lang.model.IArgument;
import eu.mihosoft.vrl.lang.model.Invocation;
import eu.mihosoft.vrl.lang.model.Type;
import eu.mihosoft.vrl.lang.model.VisualCodeBuilder;

public class ConstantExpressionPart
		extends
		AbstractCodeBuilderPart<ConstantExpression, IArgument, CodeEntity> {

	public ConstantExpressionPart(StateMachine stateMachine,
			SourceUnit sourceUnit, VisualCodeBuilder builder,
			CodeLineColumnMapper mapper) {
		super(stateMachine, sourceUnit, builder, mapper);
	}

	@Override
	public IArgument transform(ConstantExpression obj, CodeEntity parent,
			TransformContext ctx) {

		if (obj.isNullExpression()) {
			return Argument.NULL;
		} else {
			return Argument.constArg(new Type(obj.getType().getName(),
					true), obj.getValue());
		}
	}

	@Override
	public Class<ConstantExpression> getAcceptedType() {
		return ConstantExpression.class;
	}

	@Override
	public Class<CodeEntity> getParentType() {
		return CodeEntity.class;
	}

}
