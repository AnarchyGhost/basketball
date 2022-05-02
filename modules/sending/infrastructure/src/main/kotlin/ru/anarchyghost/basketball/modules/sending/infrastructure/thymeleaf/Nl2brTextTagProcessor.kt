package ru.anarchyghost.basketball.modules.sending.infrastructure.thymeleaf

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.engine.AttributeName
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.util.Validate
import org.unbescape.html.HtmlEscape

class Nl2brTextTagProcessor(templateMode: TemplateMode, dialectPrefix: String?) :
    AbstractStandardExpressionAttributeTagProcessor(templateMode, dialectPrefix, ATTR_NAME, PRECEDENCE, true) {
    override fun doProcess(
        context: ITemplateContext,
        tag: IProcessableElementTag,
        attributeName: AttributeName, attributeValue: String,
        expressionResult: Any,
        structureHandler: IElementTagStructureHandler
    ) {
        val input = expressionResult.toString()
        val text: CharSequence = produceEscapedOutput(input)
        structureHandler.setBody(text, false)
    }

    companion object {
        const val PRECEDENCE = 1300
        const val ATTR_NAME = "text"

        fun produceEscapedOutput(input: String?): String {
            return nl2br(HtmlEscape.escapeHtml4Xml(input))
        }

        fun nl2br(input: String): String {
            return "<p>$input</p>"
                .replace(Regex("[\\v\\r\\n\\f]"),"</p><p>")
                .replace(Regex("<p>\\s*</p>"),"")
        }
    }

    init {
        Validate.notNull(templateMode, "Template mode cannot be null")
        Validate.isTrue(
            templateMode == TemplateMode.HTML,
            "Unsupported template mode $templateMode. Only HTML mode is supported."
        )
    }
}