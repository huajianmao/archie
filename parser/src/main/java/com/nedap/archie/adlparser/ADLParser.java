package com.nedap.archie.adlparser;

import com.nedap.archie.adlparser.antlr.*;
import com.nedap.archie.aom.Archetype;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class ADLParser {

    public Lexer lexer;
    public AdlParser parser;
    public ADLTreeWalker walker;
    public AdlParser.AdlContext tree;

    public Archetype parse(String adl) throws IOException {
        return parse(new ANTLRInputStream(adl));
    }

    public Archetype parse(InputStream stream) throws IOException {
        return parse(new ANTLRInputStream(stream));
    }

    public Archetype parse(CharStream stream) {
        lexer = new AdlLexer(stream);
        parser = new AdlParser(new CommonTokenStream(lexer));
        tree = parser.adl(); // parse
        walker = new ADLTreeWalker();
        return walker.parse(tree);
    }

}