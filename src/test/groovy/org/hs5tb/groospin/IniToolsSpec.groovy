package org.hs5tb.groospin

import org.hs5tb.groospin.common.IniTools
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Alberto on 12-Jun-16.
 */
class IniToolsSpec extends Specification {
    @Unroll
    void fillMap() {
        setup:
        def map = [val1:null, val2:null, val3:null, val4:null, val5:null]
        File ini = new File("src/test/resources/inis/basic.ini")

        expect:
        ini.exists()

        when:
        IniTools.fillMap(ini, section, map)

        then:
        map == result

        where:
        section | result
        "a"             | [val1:null, val2:null, val3:null, val4:null, val5:null]
        "badsection1"   | [val1:null, val2:null, val3:null, val4:null, val5:null]
        "section1"      | [val1:"b", val2:"g", val3:"zz", val4:null, val5:null]
        "section2"      | [val1:"c", val2:"h", val3:null, val4:"y", val5:null]
    }

    @Unroll
    void parseIni() {
        setup:
        File ini = new File("src/test/resources/inis/basic.ini")

        expect:
        ini.exists()

        when:
        def map = IniTools.parseIni(ini, section)

        then:
        map == result

        where:
        section | result
        "a"             | [:]
        "badsection1"   | [:]
        "section1"      | [val1:"b", val2:"g", val3:"zz"]
        "section2"      | [val1:"c", val2:"h", val4:"y", val6:"zz"]
    }

    @Unroll
    void parseFullIni() {
        setup:
        File ini = new File("src/test/resources/inis/basic.ini")

        expect:
        ini.exists()

        when:
        def map = IniTools.parseIni(ini)

        then:
        map == result

        where:
        result = [(null):[no: "no"], "section1":[val1:"b", val2:"g", val3:"zz"],"section2":[val1:"c", val2:"h", val4:"y", val6:"zz"]]
    }

    @Unroll
    void parseFullIniParent() {
        setup:
        File parent = new File("src/test/resources/inis/basic.ini")
        File ini = new File("src/test/resources/inis/child.ini")

        expect:
        ini.exists()

        when:
        def map = IniTools.parseIni(ini, IniTools.parseIni(parent))

        then:
        map == result

        where:
        result = [(null):[no: "si"], "section1":[val1:"b", val2:"g", val3:"changed"],"section2":[val1:"c", val2:"h", val4:"y", val6:"zz"], "new":[f:"n"]]
    }
}
