#!/bin/sh
mvn install:install-file -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true \
    -DgroupId=jus -Dfile=lib/jus.aoo.geometrie.jar  -DartifactId=aoogeometrie
mvn install:install-file -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true \
    -DgroupId=jus -Dfile=lib/jus.util.assertion.jar -DartifactId=assertion
mvn install:install-file -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true \
    -DgroupId=jus -Dfile=lib/jus.util.jar -DartifactId=util
mvn install:install-file -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true \
    -DgroupId=jus -Dfile=lib/jus.util.geometrie.jar -DartifactId=geometrie