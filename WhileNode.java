package ast;
public class WhileNode implements ASTNode {
    public final ASTNode condition;
    public final BlockNode body;
    public WhileNode(ASTNode cond, BlockNode body) { this.condition = cond; this.body = body; }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
