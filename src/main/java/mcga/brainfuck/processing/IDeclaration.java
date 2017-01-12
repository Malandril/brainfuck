package mcga.brainfuck.processing;

import mcga.brainfuck.exceptions.InvalidCodeException;

/**
 * Interface which defines a declaration of functions or macros.
 */
@FunctionalInterface
interface IDeclaration {

    /**
     * Method called when a function is declared.
     * @param name name of the declared function
     * @param code code corresponding to the declared function
     * @param params parameters of the declared function
     * @throws InvalidCodeException if the code is invalid
     */
    void action(String name, String code, String[] params) throws InvalidCodeException;
}
