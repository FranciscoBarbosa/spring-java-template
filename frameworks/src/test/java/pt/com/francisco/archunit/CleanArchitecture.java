package pt.com.francisco.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

@AnalyzeClasses(
        packages = {
            "pt.com.francisco.app",
            "pt.com.francisco.entities",
            "pt.com.francisco.frameworks",
            "pt.com.francisco.interfaceAdapters"
        })
class CleanArchitecture {

    @Test
    void entitiesCanNotDependOnOtherLayer() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pt.com.francisco");

        ArchRule myRule =
                noClasses()
                        .that()
                        .resideInAPackage("..entities..")
                        .should()
                        .dependOnClassesThat()
                        .resideInAPackage("..usecases..")
                        .orShould()
                        .dependOnClassesThat()
                        .resideInAPackage("..frameworks..")
                        .orShould()
                        .dependOnClassesThat()
                        .resideInAPackage("..app..")
                        .orShould()
                        .dependOnClassesThat()
                        .resideInAPackage("..interfaceAdapters..");

        myRule.check(importedClasses);
    }
}
