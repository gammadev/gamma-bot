package br.com.gmfonseca.bot.shared.util.ext

import java.text.DecimalFormat

/**
 * Created by Gabriel Fonseca on 26/10/2020.
 */
fun Double.formatNumber(df: DecimalFormat = DecimalFormat("00")): String {
    return df.format(this)
}