#!/bin/bash

ls "/home/phulshof/Multimedia/Games/repositories/EmuMovies/MAME Unified Bezel Pack/" | while read zip; do

  echo "Handling file $zip."
  game_name=$(echo "$zip" | cut -d "." -f 1)
  mkdir -p "$game_name"
  cd "$game_name"
  unzip "/home/phulshof/Multimedia/Games/repositories/EmuMovies/MAME Unified Bezel Pack/$zip"

  ls Bezel*Hzt*png Bezel*Vrt*png | while read bezel; do
    background_name=$(echo "$bezel" | cut -d " " -f2-)
    background_name=$(echo "Background $background_name")
    echo "Checking for background $background_name"
    if ! [ -f "$background_name" ]; then
      echo "Moving $bezel to $background_name"
      mv "$bezel" "$background_name"
    fi
  done

  nr_hor=$(ls *Hzt* | wc -l)
  if [ "$nr_hor" == "0" ]; then
    direction="vertical"
  else
    direction="horizontal"
  fi

  echo "<mamelayout version=\"2\">"                                            > default.lay

  ls Background* | while read background; do

    rm -f t1.png t2.png t3.png
    background_name=$(echo "$background" | rev | cut -d "." -f2- | rev)
    bezel_name=$(echo "$background_name" | cut -d " " -f2-)
    bezel_name=$(echo "Bezel $bezel_name.png")
    echo "$background_name"
    convert "$background" -resize 2560x1440! t1.png

    echo ""                                                                   >> default.lay
    echo "  <element name=\"$background_name\">"                              >> default.lay
    echo "    <image file=\"$background_name.png\"/>"                         >> default.lay
    echo "  </element>"                                                       >> default.lay
    echo "  <view name=\"$background_name\">"                                 >> default.lay
    echo "    <screen index=\"0\">"                                           >> default.lay

    if [ "$direction" == "horizontal" ]; then
      convert t1.png -crop 300x1440+0+0 t2.png
      convert t1.png -crop 300x1440+2260+0 t3.png
      rm -f "$background"
      convert t2.png "../Canvas - horizontal.png" t3.png +append "$background_name.png"
      if [ -f "$bezel_name" ]; then
        echo "Using bezel $bezel_name"
        convert "$bezel_name" -resize 2560x1440 "$bezel_name"
        crop_width=$(($((2560 - $(identify -ping -format "%w" "$bezel_name"))) / 2))
        composite -geometry +"$crop_width"+0 "$bezel_name" "$background_name.png" "$background_name.png"
      else
        composite -geometry +290+0 "../Bezel - horizontal.png" "$background_name.png" "$background_name.png"
      fi
      echo "      <bounds x=\"320\" y=\"0\" width=\"1920\" height=\"1440\"/>" >> default.lay
    else
      convert t1.png -crop 705x1440+0+0 t2.png
      convert t1.png -crop 705x1440+1855+0 t3.png
      rm -f "$background"
      convert t2.png "../Canvas - vertical.png" t3.png +append "$background_name.png"
      if [ -f "$bezel_name" ]; then
        echo "Using bezel $bezel_name"
        convert "$bezel_name" -resize 2560x1440 "$bezel_name"
        crop_width=$(($((2560 - $(identify -ping -format "%w" "$bezel_name"))) / 2))
        composite -geometry +"$crop_width"+0 "$bezel_name" "$background_name.png" "$background_name.png"
      else
        composite -geometry +695+0 "../Bezel - vertical.png" "$background_name.png" "$background_name.png"
      fi
      echo "      <bounds x=\"740\" y=\"0\" width=\"1080\" height=\"1440\"/>" >> default.lay
    fi

    echo "    </screen>"                                                      >> default.lay
    echo "    <bezel element=\"$background_name\">"                           >> default.lay
    echo "      <bounds x=\"0\" y=\"0\" width=\"2560\" height=\"1440\"/>"     >> default.lay
    echo "    </bezel>"                                                       >> default.lay
    echo "  </view>"                                                          >> default.lay

    rm -f t1.png t2.png t3.png

  done

  echo ""                                                                     >> default.lay
  echo "</mamelayout>"                                                        >> default.lay
  rm -f Bezel* *.ini
  cd ..

done
