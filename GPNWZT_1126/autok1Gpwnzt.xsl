<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h1>autok</h1>
                <table>
                    <tr>
                        <th>rsz</th><th>ar</th>
                    </tr>
                <xsl:for-each select="/autok/auto">
                    <xsl:sort select="ar"/>
                    <tr>
                        <td><xsl:value-of select="@rsz"/></td>
                        <td><xsl:value-of select="ar"/></td>
                    </tr>
                </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>