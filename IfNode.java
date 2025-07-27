package ast;
public class IfNode implements ASTNode {
    public final ASTNode condition;
    public final BlockNode thenBlock;
    public final BlockNode elseBlock; // may be null
    public IfNode(ASTNode cond, BlockNode thenB, BlockNode elseB) {
        this.condition = cond; this.thenBlock = thenB; this.elseBlock = elseB;
    }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
