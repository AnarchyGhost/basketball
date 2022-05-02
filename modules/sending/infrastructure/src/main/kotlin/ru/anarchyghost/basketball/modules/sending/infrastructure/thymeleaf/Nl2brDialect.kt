package ru.anarchyghost.basketball.modules.sending.infrastructure.thymeleaf

import org.thymeleaf.dialect.AbstractProcessorDialect
import org.thymeleaf.processor.IProcessor
import org.thymeleaf.templatemode.TemplateMode


class Nl2brDialect : AbstractProcessorDialect(
    "Dialect",
    "nl2br",
    1000
) {
    override fun getProcessors(dialectPrefix: String): Set<IProcessor> {
        val processors: MutableSet<IProcessor> = HashSet()
        processors.add(Nl2brTextTagProcessor(TemplateMode.HTML, dialectPrefix))
        return processors
    }
}