package io.pivotal.intellij.jasmine.util

import com.intellij.lang.javascript.psi.JSFile
import com.intellij.openapi.vfs.VirtualFile

/**
 * Utility class for handling Jasmine files
 */
object JasmineFileUtil {
    /**
     * Checks if a JavaScript file is a Jasmine test file
     * Uses file name patterns and content analysis
     */
    fun isJasmineTestFile(jsFile: JSFile): Boolean {
        val virtualFile = jsFile.virtualFile ?: return false
        
        // First check file name patterns
        if (hasJasmineTestFileName(virtualFile)) {
            return true
        }
        
        // Then check file content for Jasmine test constructs
        val fileContent = jsFile.text.lowercase()
        return fileContent.contains("describe(") && 
               (fileContent.contains("it(") || fileContent.contains("test(")) &&
               (fileContent.contains("expect(") || fileContent.contains("jasmine."))
    }
    
    /**
     * Checks if a file has a Jasmine test file name pattern
     */
    fun hasJasmineTestFileName(file: VirtualFile): Boolean {
        val fileName = file.nameWithoutExtension.lowercase()
        return fileName.endsWith(".spec") || 
               fileName.endsWith(".test") || 
               fileName.contains("spec")
    }
}
