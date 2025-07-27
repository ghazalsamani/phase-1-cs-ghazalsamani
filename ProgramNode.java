package ast;
import java.util.List;

public class ProgramNode implements ASTNode {
    public final List<ASTNode> statements;
    public ProgramNode(List<ASTNode> stmts) { this.statements = stmts; }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
