package mcga.brainfuck.processing;

import mcga.brainfuck.exceptions.InvalidCodeException;

/**
 * Created by Thomas on 07/01/2017.
 */
@FunctionalInterface
interface IDeclaration {
    void action(String name, String code, String[] params) throws InvalidCodeException;
}
