
# TagSQL

  

<strong>TagSQL = Tag code + SQL script</strong>, the key idea is append Tag code in SQL script comment, it similar to some template language solution, but more flexible and extendable; A bit trick but big advantage that make write SQL script phase to running mode smoothly, it is simply but efficiently🚀.

  

# Motivation

TagSQL purpose is separate SQL script from Java code, never hardcode or embed SQL script in Java code, free code then free time, enjoy!

  

# Usage

  

Use by Springboot statrer

    <dependency>
    <groupId>io.github.tag-sql</groupId>
    <artifactId>tagsql-springboot-starter</artifactId>
    <version>0.1.2</version>
    </dependency>
    
or directly:
    
    <dependency>
    <groupId>io.github.tag-sql</groupId>
    <artifactId>tagsql-core</artifactId>
    <version>0.1.1</version>
    </dependency>
