package mcga.brainfuck.processing;

import mcga.brainfuck.ProcedureStruct;

/**
 * Created by Thomas on 07/01/2017.
 */
class FunctionDeclaration implements IDeclaration {
    boolean function;
    ProcedureStruct struct;
    
    FunctionDeclaration(boolean function) {
        this.function = function;
    }
    
    @Override
    public void action(String name, String code, String[] params) throws Exception {
        struct = new ProcedureStruct(code, params, function);
        Parser.procedureMap.put(name, struct);
    }
}
