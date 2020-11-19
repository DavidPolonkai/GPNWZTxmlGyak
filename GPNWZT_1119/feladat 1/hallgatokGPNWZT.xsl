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
                        <th>id</th><th>keresztnév</th><th>vezetéknév</th><th>becenév</th><th>fizetés</th>
                    </tr>
                <xsl:for-each select="osztaly/alkalmazott">
                    <tr>
                        <td><xsl:value-of select="attribute::id"/></td>
                        <td><xsl:value-of select="keresztnev"/></td>
                        <td><xsl:value-of select="vezeteknev"/></td>
                        <td><xsl:value-of select="becenev"/></td>
                        <td><xsl:value-of select="fizetes"/></td>

                    </tr>
                </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>