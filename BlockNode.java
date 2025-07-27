package ast;
import java.util.List;
public class BlockNode implements ASTNode {
    public final List<ASTNode> statements;
    public BlockNode(List<ASTNode> stmts) { this.statements = stmts; }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
