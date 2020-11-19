<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <meta charset="UTF-8"/>
            <style>
                body{
                margin: 0px;
                background-color: black;
                color: white;
                }
                table,th,tr,td{
                border-collapse: collapse;
                border: 2px solid gray;
                }
                table{
                margin-left: auto;
                margin-right: auto;
                min-width: 80%;
                }
                h1{
                text-align: center;
                }
            </style>
            <body>
                <h1>Emberek</h1>
                <table>
                    <tr>
                        <th>tipus</th><th>tárgy</th><th>nap</th><th>tól</th><th>ig</th><th>helyszín</th><th>oktató</th><th>szak</th>
                    </tr>
                    <xsl:for-each select="orarend/ora">
                        <tr>
                            <td><xsl:value-of select="attribute::tipus"/></td>
                            <td><xsl:value-of select="targy"/></td>

                            <td><xsl:value-of select="idopont/nap"/></td>
                            <td><xsl:value-of select="idopont/tol"/></td>
                            <td><xsl:value-of select="idopont/ig"/></td>

                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>

                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>