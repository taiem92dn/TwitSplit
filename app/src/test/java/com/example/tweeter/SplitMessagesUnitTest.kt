package com.example.tweeter

import com.example.tweeter.util.SplitMessagesUtil
import org.junit.Assert.assertEquals
import org.junit.Test


class SplitMessagesUnitTest {


    fun `test chunks length`(message :String, result : List<String>) {
        assertEquals(true, result.size >= message.length / 50)

        for (s in result) {
            if (s.length > 50) {
                println("error ${result.indexOf(s)} size ${result.size}: $s")

            }
            assertEquals(true, s.length <= 50)
        }
    }

    @Test
    fun  `test empty message`() {
        assertEquals(ArrayList<String>(), SplitMessagesUtil.splitMessage(""))
    }

    @Test
    fun `test message no need split`() {
        val message = "I can't believe Tweeter now supports chunking"

        `test chunks length`(message, SplitMessagesUtil.splitMessage(message)!!)
        assertEquals(arrayListOf(message), SplitMessagesUtil.splitMessage(message))
    }

    @Test
    fun `test message has 2 chunk`() {
        val message = "I can't believe Tweeter now supports chunking my messages, so I" +
                " don't have to do it myself."

        val result = arrayListOf("1/2 I can't believe Tweeter now supports chunking",
                "2/2 my messages, so I don't have to do it myself.")

        `test chunks length`(message, SplitMessagesUtil.splitMessage(message)!!)
        assertEquals(result, SplitMessagesUtil.splitMessage(message))
    }

    @Test
    fun `test very long message`() {
        val message = "Lorem ipsum dolor sit amet, nam modo nusquam docendi ei. Ad utroque lobortis has. Vis ancillae atomorum hendrerit et. Ut modo detraxit adolescens usu, sed an tempor latine tibique. Illud cotidieque mei te.\n" +
                "\n" +
                "Ne nonumes graecis iudicabit eos. Sit te alia laudem docendi, sed facilis definitiones eu. Has cu natum mundi pertinacia, ne rebum instructior per. Cum ex sumo vidisse appellantur. Modo nobis cu vel, nec quando dissentiunt no. Duo ad similique democritum definitionem, pri aeterno viderer convenire ne.\n" +
                "\n" +
                "Ut cum enim nominati, est at rebum neglegentur. In dolor noster audire cum, per mentitum hendrerit ut. Aliquip fabulas in usu, audire appareat cotidieque ex eum, ne mel fugit option sententiae. Summo complectitur cu eam. Qui regione persequeris an, et similique concludaturque est.\n" +
                "\n" +
                "Cu ullum omittantur quo. Usu enim fierent intellegat te, quo veritus assentior at, habeo primis ei vim. Eam vero soleat inimicus eu, sit impetus sapientem temporibus at. Ex quem purto temporibus eos. Iudico mucius adolescens ne quo, commodo meliore efficiendi ad pri. Vix eu utinam ridens dissentiet, eos ex enim similique.\n" +
                "\n" +
                "Ea pri pericula efficiantur, error vidisse interesset an eam, cu ius epicuri expetendis instructior. Quod recusabo adversarium ex his, sed omnes solet inermis te. Viderer iuvaret dolores vim ne, ceteros convenire qui te. Tempor eirmod et vim, possim deserunt dissentiet te sit. Has vitae essent postulant an, augue repudiare adolescens nec at.\n" +
                "\n" +
                "Velit suscipit quaerendum sit cu, at ferri ullum his. Id veri munere insolens sit, ea eam quis aliquid forensibus. Pro meis similique id. An augue tacimates urbanitas ius. Menandri perpetua eu quo, nec cu lorem affert legere.\n" +
                "\n" +
                "Postea indoctum sit at. Summo essent vivendum nec at, no ius ferri concludaturque. Ne denique salutandi eum. Dolorum perfecto corrumpit no has, cum ut aliquam instructior. Sea debitis singulis an. In primis consetetur nec.\n" +
                "\n" +
                "At volumus pertinax vim, dicat neglegentur consequuntur vix no. Esse scaevola gubergren et sed, mei partem nemore aliquip te. Ad mei ipsum platonem. Salutandi voluptatum vim ex, novum recusabo per no, sed utroque officiis antiopam in. Has ea quas nemore. Has et inani tollit, nec labore senserit an.\n" +
                "\n" +
                "Ut sea tibique omnesque, an dicam mnesarchum usu. Deleniti sensibus mel in. Et ius quot reformidans suscipiantur, no minim minimum pri. In dicat nostrud quo.\n" +
                "\n"



        `test chunks length`(message, SplitMessagesUtil.splitMessage(message)!!)
//        assertEquals(" ", SplitMessagesUtil.splitMessage(message))
    }

    @Test
    fun `test message with min chunk length`() {
        val message = "12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123 12345678901234567890123"

        val result = arrayListOf("1/2 I can't believe Tweeter now supports chunking",
                "2/2 my messages, so I don't have to do it myself.")

        `test chunks length`(message, SplitMessagesUtil.splitMessage(message)!!)
        val expect = arrayListOf("1/12 12345678901234567890123", "2/12 12345678901234567890123", "3/12 12345678901234567890123", "4/12 12345678901234567890123", "5/12 12345678901234567890123"
                , "6/12 12345678901234567890123", "7/12 12345678901234567890123", "8/12 12345678901234567890123", "9/12 12345678901234567890123", "10/12 12345678901234567890123", "11/12 12345678901234567890123", "12/12 12345678901234567890123")
        assertEquals(expect, SplitMessagesUtil.splitMessage(message))
    }

    @Test
    fun `test error message`() {
        val message = "12345678901234567890123456789012345678901234567 1234567980 1234567890"

        assertEquals(ArrayList<String>(), SplitMessagesUtil.splitMessage(message))
    }

    @Test
    fun `test random message`() {
        val message = "a                                                a                       b                  "

        `test chunks length`(message, SplitMessagesUtil.splitMessage(message)!!)

    }

    @Test
    fun `test calculate total indicator length`() {
        assertEquals(28, SplitMessagesUtil.calTotalIndicatorLength(9))
        assertEquals(87, SplitMessagesUtil.calTotalIndicatorLength(19))
        assertEquals(487, SplitMessagesUtil.calTotalIndicatorLength(99))
    }

    @Test
    fun `test estimate chunksize`() {
        assertEquals(2, SplitMessagesUtil.calEstimateChunkSize(23*3+2))
    }
}