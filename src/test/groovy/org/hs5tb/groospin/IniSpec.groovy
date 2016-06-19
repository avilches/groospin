package org.hs5tb.groospin

import org.hs5tb.groospin.common.Ini
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Alberto on 12-Jun-16.
 */
class IniSpec extends Specification {
    @Unroll
    void "ini no section with parent"() {
        setup:
        Ini ini = new Ini()

        when:
        ini.parse(new File("impossible"))

        then:
        noExceptionThrown()

        when:
        ini["a"] = "a"
        ini["B"] = "b"

        then:
        ini["A"] == "a"
        ini["a"] == "a"
        ini[" a"] == "a"
        ini["b"] == "b"

        when:
        ini.parent = new Ini().putAll(["a": "no", "c": "c"])

        then:
        ini["a"] == "a"
        ini["c"] == "c"

        and:
        ini.defaultSection == ["a": "a", "c": "c", "b": "b"]

        when:
        ini.putAll("section ", ["v":"1"])
        ini.parent.putAll(" section", ["v": "11", " W ": "2"])

        then:
        ini.get("section", " V ") == "1"
        ini.get(" section ", "w") == "2"

        and:
        ini.getSection(" section ") == ["v": "1", "w": "2"]
    }

    @Unroll
    void "parse ini by section and duplicateds"() {
        setup:
        File iniFile = new File("src/test/resources/inis/basic.ini")

        expect:
        iniFile.exists()

        when:
        Ini ini = new Ini().parse(iniFile, section)

        then:
        ini.getSection(section) == result

        where:
        section       | result
        "a"           | [:]
        "badsection1" | [:]
        "section1"    | [val1: "a", val2: "g", val3: "z"]
        "ignored"     | [:]
        "section2"    | [val1: "c", val2: "h", val4: "y", val6: "zz"]
    }

    @Unroll
    void "parse ini by section and fixed keys"() {
        setup:
        File iniFile = new File("src/test/resources/inis/basic.ini")

        expect:
        iniFile.exists()

        when:
        Ini ini = new Ini().parse(iniFile, section, ["no", "val1", "VAL2", "val3", "vaL4", "val5"])

        then:
        ini.getSection(section) == result

        where:
        section            | result
        Ini.defaultSection | [no: "no"]
        "badsection1"      | [:]
        "section1"         | [val1: "a", val2: "g", val3: "z"]
        "section2"         | [val1: "c", val2: "h", val4: "y"]
    }


    @Unroll
    void "parse full ini with parent"() {
        setup:
        File iniFile = new File("src/test/resources/inis/basic.ini")
        File childFile = new File("src/test/resources/inis/child.ini")

        expect:
        childFile.exists()

        when:
        Ini ini = new Ini().parse(childFile)
        ini.parent = new Ini().parse(iniFile)

        then:
        ini.getSection(section) == result

        where:
        section            | result
        Ini.defaultSection | [no: "si"]
        "section1"         | [val1: "a", val2: "g", val3: "changed"]
        "section2"         | [val1: "c", val2: "h", val4: "y", val6: "zz"]
        "new"              | [f: "n"]
    }
}
