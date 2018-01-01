<xsl:stylesheet
        version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:hos="http://nure.ua/hostel"
        xmlns="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="html" indent="no"/>

    <xsl:template match="/">
        <html>
            <body>
                <table width="100%" style="border:1px solid black">
                    <xsl:for-each select="hos:hostel/hos:floor">
                        <tr>
                            <td width="10%" style="border:1px solid black; ">Floor id - <xsl:value-of select="@id"/></td>
                            <td width="90%" >
                                <table width="100%" >
                                    <xsl:for-each select="hos:room">
                                        <tr style="border:1px solid black">
                                            <td width="25%" style="border:1px solid black; ">Room id - <xsl:value-of select="@id"/></td>
                                            <td width="75%" >
                                                <table width="100%" style="border:1px solid black">
                                                    <xsl:for-each select="hos:student">
                                                        <tr><td width="100%" style="border:1px solid black; ">Student id - <xsl:value-of select="@pid"/><p>
                                                            <ul>
                                                                <li>Name - <xsl:value-of select="hos:name"/></li>
                                                                <li>Surname - <xsl:value-of select="hos:surname"/></li>
                                                                <li>Middlename - <xsl:value-of select="hos:middlename"/></li>
                                                                <li>Phone number - <xsl:value-of select="hos:phone"/></li>
                                                                <li>Date of birth - <xsl:value-of select="hos:dob"/></li>
                                                                <li>Spec - <xsl:value-of select="hos:spec"/>, level - <xsl:value-of
                                                                        select="hos:level"/>, grade - <xsl:value-of
                                                                        select="hos:grade"/></li>
                                                                <li>Payment: balance - <xsl:value-of select="hos:payment/hos:balance"/> <xsl:if
                                                                        test="not(hos:payment/hos:subsidy)">, no subsidy</xsl:if> <xsl:if
                                                                        test="hos:payment/hos:subsidy">, subsidy: <xsl:value-of
                                                                        select="hos:payment/hos:subsidy/hos:price"/>, estimates - <xsl:value-of
                                                                        select="hos:payment/hos:subsidy/hos:estimate"/></xsl:if></li>
                                                                <li>Medical - <xsl:value-of select="*[self::hos:medical/hos:isExists or self::hos:medical/hos:expirationDate]"/></li>
                                                            </ul>
                                                        </p></td></tr>
                                                    </xsl:for-each>
                                                </table>
                                            </td>
                                        </tr>в
                                    </xsl:for-each>
                                </table>
                            </td>

                        </tr>
                    </xsl:for-each>
                </table>

                <h2>DOLZHNIKI:</h2>
                
                <table style="border-collapse: collapse;">
                    <tr>
                        <th style="border: 1px solid #800;">Name</th>
                        <th style="border: 1px solid #800;">Room</th>
                        <th style="border: 1px solid #800;">Balace</th>
                    </tr>
                    <xsl:for-each select="hos:hostel/hos:floor">
                        <xsl:for-each select="hos:room">
                            <xsl:for-each select="hos:student">
                                <xsl:if test="hos:payment/hos:balance &lt; 0" >
                                    <tr>
                                        <td style="border: 1px solid #800;"><xsl:value-of select="hos:name"/>_<xsl:value-of select="hos:surname"/></td>
                                        <td style="border: 1px solid #800;"><xsl:value-of select="../@id"/></td>
                                        <td style="border: 1px solid #800;"><xsl:value-of select="hos:payment/hos:balance"/></td>
                                    </tr>
                                </xsl:if>
                            </xsl:for-each>
                        </xsl:for-each>
                    </xsl:for-each>
                </table>


            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>


