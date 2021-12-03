package com.edmundo.blisschallenge

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.runner.RunWith

@RunWith(ArchUnitRunner::class)
@AnalyzeClasses(packages = ["com.edmundo.blisschallenge"])
class ArchPackagesTest {

    private val featurePackages = arrayOf(
        "domain",
        "general",
        "github"
    ).map {
        "..$it.."
    }.toTypedArray()

    fun testPrefix(
        filteredPackage: List<String>,
        packageName: String,
        importedClasses: JavaClasses
    ) {

        classes()
            .that()
            .resideInAPackage(packageName)
            .should()
            .onlyHaveDependentClassesThat()
            .resideOutsideOfPackages(*filteredPackage.toTypedArray())
            .check(importedClasses)
    }

    @ArchTest
    fun `isolate package domain`(importedClasses: JavaClasses) {
        val filteredPackage = featurePackages.filter { it != "..domain.." }
        val packageName = "..domain.."
        testPrefix(filteredPackage, packageName, importedClasses)
    }

    @ArchTest
    fun `isolate package github`(importedClasses: JavaClasses) {
        val filteredPackage = featurePackages.filter { it != "..github.." }
        val packageName = "..github.."
        testPrefix(filteredPackage, packageName, importedClasses)
    }

    @ArchTest
    fun `isolate package general`(importedClasses: JavaClasses) {

        val filteredPackage = featurePackages.filter { it != "..general.." }

        classes()
            .that()
            .resideInAPackage("..general..")
            .should()
            .onlyDependOnClassesThat()
            .resideOutsideOfPackages(*filteredPackage.toTypedArray())
            .check(importedClasses)
    }
}