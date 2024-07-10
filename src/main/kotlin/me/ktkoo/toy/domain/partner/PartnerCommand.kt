package me.ktkoo.toy.domain.partner

import org.eclipse.jdt.internal.compiler.codegen.ConstantPool.ToString


class PartnerCommand(
    private val partnerName: String,
    private val businessNo: String,
    private val email: String,
) {
    fun toEntity(): Partner {
        return Partner(
            partnerName = partnerName,
            businessNo = businessNo,
            email = email,
        )
    }
}
