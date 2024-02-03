package pt.com.francisco.frameworks.archunit;

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
class CleanArchitectureTest {

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

    @Test
    void useCasesCannotDependOnOutterLayers() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pt.com.francisco");

        ArchRule myRule =
                noClasses()
                        .that()
                        .resideInAPackage("..usecases..")
                        .should()
                        .dependOnClassesThat()
                        .resideInAPackage("..frameworks..")
                        .orShould()
                        .dependOnClassesThat()
                        .resideInAPackage("..interfaceadapters..");

        myRule.check(importedClasses);
    }

    @Test
    void interfaceAdaptersCannotDependOnFrameworks() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pt.com.francisco");

        ArchRule myRule =
                noClasses()
                        .that()
                        .resideInAPackage("..interfaceadapters..")
                        .should()
                        .dependOnClassesThat()
                        .resideInAPackage("..frameworks..");

        myRule.check(importedClasses);
    }

    @Test
    void businessLayersShouldNotKnowAboutSpringDependencies() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("pt.com.francisco");

        ArchRule myRule =
                noClasses()
                        .that()
                        .resideInAPackage("..entities..")
                        .or()
                        .resideInAPackage("..usecases..")
                        .should()
                        .dependOnClassesThat()
                        .resideInAPackage("..springframework..");

        myRule.check(importedClasses);
    }
}
