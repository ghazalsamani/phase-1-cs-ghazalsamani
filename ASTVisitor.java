package ast;

public interface ASTVisitor<T> {
    T visit(ProgramNode node);
    T visit(VarDeclNode node);
    T visit(AssignNode node);
    T visit(IfNode node);
    T visit(WhileNode node);
    T visit(BlockNode node);
    T visit(BinaryOpNode node);
    T visit(IntLiteralNode node);
    T visit(VarRefNode node);
}
