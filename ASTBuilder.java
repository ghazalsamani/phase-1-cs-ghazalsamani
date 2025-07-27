package visitor;
import ast.*;
import parser.SimpleLangParser;
import parser.SimpleLangBaseVisitor;
import java.util.*;

public class ASTBuilder extends SimpleLangBaseVisitor<ASTNode> {
    @Override public ASTNode visitProgram(parser.SimpleLangParser.ProgramContext ctx) {
        List<ASTNode> stmts = new ArrayList<>();
        for(var s: ctx.statement()) stmts.add(visit(s));
        return new ProgramNode(stmts);
    }
    @Override public ASTNode visitVarDecl(parser.SimpleLangParser.VarDeclContext ctx) {
        return new VarDeclNode(ctx.ID().getText());
    }
    @Override public ASTNode visitAssignment(parser.SimpleLangParser.AssignmentContext ctx) {
        return new AssignNode(ctx.ID().getText(), visit(ctx.expr()));
    }
    @Override public ASTNode visitIfStmt(parser.SimpleLangParser.IfStmtContext ctx) {
        var cond = visit(ctx.expr());
        var thenB = (BlockNode) visit(ctx.block(0));
        BlockNode elseB = ctx.block().size()>1 ? (BlockNode)visit(ctx.block(1)) : null;
        return new IfNode(cond, thenB, elseB);
    }
    @Override public ASTNode visitWhileStmt(parser.SimpleLangParser.WhileStmtContext ctx) {
        return new WhileNode(visit(ctx.expr()), (BlockNode) visit(ctx.block()));
    }
    @Override public ASTNode visitBlock(parser.SimpleLangParser.BlockContext ctx) {
        List<ASTNode> stmts = new ArrayList<>();
        for(var s: ctx.statement()) stmts.add(visit(s));
        return new BlockNode(stmts);
    }
    @Override public ASTNode visitExpr(parser.SimpleLangParser.ExprContext ctx) {
        if(ctx.INT()!=null) return new IntLiteralNode(Integer.parseInt(ctx.INT().getText()));
        if(ctx.ID()!=null)  return new VarRefNode(ctx.ID().getText());
        if(ctx.op!=null)   return new BinaryOpNode(visit(ctx.expr(0)), ctx.op.getText(), visit(ctx.expr(1)));
        return visit(ctx.expr(0)); // parentheses
    }
}
