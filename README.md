Interpreter
===========
This is an Interpreter written in Java for a language called "SILLY" with the following grammar:


    <statement> --> <assignment> | <output> | <if> | <while> | <repeat> | <quit>
	
    <assignment> --> <identifier> '=' <expression>

    <expression> --> <add_expr> | <other_expr>
    <add_expr>   --> <term> | '(' <term> { '+' <term> } ')'    
    <other_expr> --> '(' <int_term> { ('+' | '-' | '*' | '/') <int_term> } ')'
	
    <output>     --> 'output' <expression>
    
    <if>         --> 'if' <expression>
                       { <statement> } 
                     [ 'else'
                       { <statement> } ] 
                     'end'
                     
    <while>      --> 'while' <expression>
                       { <statement> } 
                     'end'   
                     
    <repeat>     --> 'repeat' <expression>
                       { <statement> }
                     'end'  

    <subdecl>    --> 'sub' <identifier> '(' { <identifier> } ')'
                        [ 'local' '(' { <identifier> } ')' ]
                        { <statement> }
                     'end'

    <subcall>    --> 'call' <identifier> '(' { <expression> } ')'
                        
    <quit>       --> 'quit'
    
    <quit>       --> 'quit'
    
    <identifier> --> <letter> { <letter> | <digit> }
    <integer>    --> [ - ] <digit> { <digit> }
    <string>     --> '"' { <char> } '"'
    
    <int_term>   --> <integer> | <identifier> 
    <term>       --> <string> | <int_term>

    <letter>     --> 'a' | 'b' | 'c' | ... | 'z' | 'A' | 'B' | 'C' | ... | 'Z'
    <digit>      --> '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' 
    <char>       --> any non-whitespace character other than '"' 
