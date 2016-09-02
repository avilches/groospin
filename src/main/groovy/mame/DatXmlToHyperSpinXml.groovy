package mame

import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.MameMachine
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 16-Aug-16.

 i've made an automatic script that use catver.ini (http://www.progettosnaps.net/catver/) to keep all the genre exept thoses that contains :
 Paddle,Electromechanical,Utilities,Game Console,Print Club,Multi-cart Board,Machine,Business Computer,Pocket Computer Games,Terminal Games,Notebook,PDA Games,ComputerPhone Games,Workstation,DVD Reader,SCSI Controller,Barcode Printer,Astrological Computer,Drum Machine,Gambling Board,Audio Sequencer,Portable Media Player,Mobile Phone,Development Computer,Electronic Game,Home Computer,Kit Computer,Matrix Printer,Microcomputer,Punched Card Computer,Single Board Computer,Synthesiser,Training Board,Cash Counter,Clock,Document Processors,Electronic Typewriter,EPROM Programmer,Graphics Display Controller,Network Processor,Printer Handbook,Satellite Receiver,Speech Synthesiser,Word-processing Machine,Handheld Child Computers,Dot-Matrix Display,Test ROM,3D Printer,Graphic Tablet,In Circuit Emulator,DVD Player,Robot Control,Thermal Printer,VTR Control.

 my actual script (mame0.175 full lists) use the command : mame -listxml >mamexml.txt
 then i ' ve made filters for machines from this list and check for each filtered machines the genre from catver.ini (that not contains the system list) then write the xml
 I will try to set different order :
 check for all machine from catver.ini (that not contains the system list) the machine tag from mamexml.txt then write the xml
 i think that on this order there will be more results because of less filters.
 ( but the chd will still not appears because they are not listed on catver.ini )
 I will made a test with the mame 0.175 and check the results.If all is ok i will use this method to next release (mame 0.176)
 My script also use nplayer.ini (http://nplayers.arcadebelgium.be/) so i need to wait for both files to create all lists.

 r0man0
 */

class DatXmlToHyperSpinXml {

    static transform(String from, String to, Map header, Closure filter = null) {
        println "Parsing MAME dat ${from}..."
        transform(MameMachine.parseDat(from), to, header, filter)
    }

    static transform(Node dat, String to, Map header, Closure filter = null) {
        List<MameMachine> roms = MameMachine.loadRoms(dat, filter)
        HyperSpinDatabase.write(roms, new File(to), header)
    }


}