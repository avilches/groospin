//------------------------------------------------------------------------------
//                       Copyright 2014, P. Hulshoff
//                             The Netherlands
//------------------------------------------------------------------------------
//
// This program is free software; you can use it in any way you like. I
// hereby declare that I will not excercise my copyrights on this version of
// this program.  Claiming that you wrote this code is discouraged; I'm not
// that good.  :)
//
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//
//------------------------------------------------------------------------------


#include <sstream>
#include <fstream>
#include <iostream>
#include <string>
#include <sys/stat.h>
#include <sys/types.h>
#include <vector>
#include <algorithm>
using namespace std;


//------------------------------------------------------------------------------
//                                Constants
//------------------------------------------------------------------------------


const int c_none                 = 0;
const int c_no_casino            = 1;
const int c_no_clones            = 1 <<  1;
const int c_no_mahjong           = 1 <<  2;
const int c_no_mature            = 1 <<  3;
const int c_no_quiz              = 1 <<  4;
const int c_no_electromechanical = 1 <<  5;
const int c_no_utilities         = 1 <<  6;
const int c_no_playchoice        = 1 <<  7;
const int c_vertical             = 1 <<  8;
const int c_horizontal           = 1 <<  9;
const int c_non_working          = 1 << 10;


//------------------------------------------------------------------------------
//                                 Classes
//------------------------------------------------------------------------------


// Game class
class game
{
  public:
    string name;
    string file;
    string year;
    string score;
    string genre;
    string rating;
    string clone_of;
    string version;
    string manufacturer;
    string crc;
    string orientation;
    string players;
    string ctrltype;
    string buttons;
    string joyways;
    bool   working;
    game   *next;
    game   *prev;
    game( string gname, string gfile, string gyear, string gclone_of );
};


// Create empty game
game::game( string gname, string gfile, string gyear, string gclone_of )
{
  name        = gname;
  file        = gfile;
  year        = gyear;
  clone_of    = gclone_of;
  version     = "";
  genre       = "";
  rating      = "";
  crc         = "";
  orientation = "horizontal";
  players     = "";
  ctrltype    = "";
  buttons     = "";
  joyways     = "";
  next        = NULL;
  prev        = NULL;
}


// Sort the list of games.
// It expects list either to point to the start of a list, or to have the list be circular
game *sort( game *list, int nr_games )
{

  // Error situation
  if ( list == NULL || nr_games == 0 )
    return NULL;

  // Default situation
  if ( nr_games == 1 )
  {
    (*list).prev = list;
    (*list).next = list;
    return list;
  }

  // Define left list
  game *left = list;
  if ( (*left).prev != NULL )
  {
    (*((*left).prev)).next = NULL;
    (*left).prev           = NULL;
  }

  // Define right list
  game *right = list;
  for ( int i = 1; i <= nr_games/2; i++ )
    right = (*right).next;
  if ( (*right).prev != NULL )
  {
    (*(*right).prev).next = NULL;
    (*right).prev         = NULL;
  }

  // Sort the lists
  left  = sort( left, nr_games/2 );
  right = sort( right, nr_games-nr_games/2 );

  // Disconnect the lists
  (*((*left).prev)).next  = NULL;
  (*left).prev            = NULL;
  (*((*right).prev)).next = NULL;
  (*right).prev           = NULL;

  // Resulting gamelist
  game *result;

  if ( (*left).name < (*right).name )
  {
    result = left;
    left = (*left).next;
  }
  else
  {
    result = right;
    right = (*right).next;
  }
  
  game *loop_game = result;
  while ( left != NULL || right != NULL )
  {
    if ( right == NULL || 
        (left  != NULL &&
        ((*left).name < (*right).name)) )
    {
      (*loop_game).next = left;
      (*left).prev      = loop_game;
      loop_game         = (*loop_game).next;
      left              = (*left).next;
    }
    else
    {
      (*loop_game).next = right;
      (*right).prev     = loop_game;
      loop_game         = (*loop_game).next;
      right             = (*right).next;
    }
  }  

  // Reconnect the first and last game
  (*loop_game).next = result;
  (*result).prev    = loop_game;

  return result;
}


//------------------------------------------------------------------------------
//                            Global variables
//------------------------------------------------------------------------------


vector<string> mame_systems;
vector<game*>  mame_games;
vector<int>    nr_mame_games;


//------------------------------------------------------------------------------
//                                Functions
//------------------------------------------------------------------------------


// Read the file "extra_info.txt", and insert additional information into the MAME list.
void read_extra_info( )
{

  string    line;
  string    extra_info_string = "extra_info.txt";
  ifstream  fextra_info( extra_info_string.c_str( ) );

  cout << "Reading extra_info.txt, and inserting additional information into the MAME list." << endl;

  string game_name = "";
  string score     = "";
  string genre     = "";
  string rating    = "";
  bool   found     = false;

  while ( getline( fextra_info, line ) )
  {

    if ( line.find( "=", 0 ) != string::npos ) // strip unused lines
    {

      // Get filename
      if (string::size_type pos = line.find( "name   =", 0 ) != string::npos)
      {
        game_name = line.erase( 0, pos+8 );
        score     = "";
        genre     = "";
        rating    = "";
      }

      // Get score
      if (string::size_type pos = line.find( "score  =", 0 ) != string::npos)
        score = line.erase( 0, pos+8 );

      // Get genre
      if (string::size_type pos = line.find( "genre  =", 0 ) != string::npos)
        genre = line.erase( 0, pos+8 );

      // Get rating
      if (string::size_type pos = line.find( "rating =", 0 ) != string::npos)
      {
        rating = line.erase( 0, pos+8 );
        game *loop_game = mame_games[0];
        do
        {
          if ( loop_game->file     == game_name ||
               loop_game->clone_of == game_name )
          {
            if ( loop_game->file == game_name || loop_game->score == "" )
              loop_game->score  = score;
            if ( loop_game->file == game_name || loop_game->genre == "" )
            loop_game->genre  = genre;
            if ( loop_game->file == game_name || loop_game->rating == "" )
              loop_game->rating = rating;
            found = true;
          }
          loop_game = (*loop_game).next;
        }
        while ( loop_game != mame_games[0] );
        if ( found )
          found = false;
        else
          cout << "Could not find matching game for " << game_name << " found in extra_info.txt" << endl;
      }
    }
  }

  fextra_info.close( );

  return;

}


// Read the MAME output, and generate an alphabetical list of games
void create_mame_games( )
{

  FILE *gamelist_file;
  uint index = 0;
  bool is_mame_list;

  if ( mame_systems.size( ) == 0 ) // Start with Arcade games
  {

    // Initialise the systems list with MAME
    //
    mame_systems.push_back( "mame" );
    mame_games.push_back( NULL );
    nr_mame_games.push_back( 0 );

    if ((gamelist_file = popen( "mamearcade -listxml", "r" )) == NULL)
    {
      cout << "Cannot get arcade game list information from MAME." << endl;
      return;
    }
    cout << "Reading arcade list." << endl;
    is_mame_list = true;

  }
  else // Software lists
  {

    if ((gamelist_file = popen( "mame -listsoftware", "r" )) == NULL)
    {
      cout << "Cannot get softwarelist game list information from MAME." << endl;
      return;
    }
    is_mame_list = false;

  }
  
  char           char_buf[BUFSIZ];
  vector<string> gamelist;

  while ( fgets( char_buf, sizeof ( char_buf ), gamelist_file ) )
  {
    string string_buf = char_buf;
    gamelist.push_back( string_buf );
  }
  pclose( gamelist_file );


  for ( uint i = 0; i < gamelist.size( ); i++)
  {

    if ( !is_mame_list && gamelist[i].find( "softwarelist name=", 0 ) != string::npos ) // Software list found
    {
      string::size_type pos1 = gamelist[i].find( "softwarelist name=\"", 0 ) + 19;
      string::size_type pos2 = gamelist[i].find( "\"", pos1 ) - pos1;
      string machine = gamelist[i].substr( pos1, pos2 );
      mame_systems.push_back( machine );
      mame_games.push_back( NULL );
      nr_mame_games.push_back( 0 );
      index++;
      cout << "Reading software list " << machine << "." << endl;
    }

    if ( ( is_mame_list && gamelist[i].find( "machine name=",  0 ) != string::npos
                        && gamelist[i].find( "ismechanical=",  0 ) == string::npos
                        && gamelist[i].find( "isbios=",        0 ) == string::npos) ||
         (!is_mame_list && gamelist[i].find( "software name=", 0 ) != string::npos) ) // Game found
    {

      string::size_type pos1;
      if ( is_mame_list )
        pos1 = gamelist[i].find( "machine name=\"",     0 ) + 14;
      else
        pos1 = gamelist[i].find( "software name=\"", 0 ) + 15;
      string::size_type pos2 = gamelist[i].find( "\"", pos1 ) - pos1;
      string gfile = gamelist[i].substr( pos1, pos2 );

      string gclone_of = "";
      if ( gamelist[i].find( "cloneof=\"", 0 ) != string::npos )
      {
        string::size_type pos1 = gamelist[i].find( "cloneof=\"", 0 ) + 9;
        string::size_type pos2 = gamelist[i].find( "\"", pos1 ) - pos1;
        gclone_of = gamelist[i].substr( pos1, pos2 );
      }

      while ( gamelist[i].find( "<description>", 0 ) == string::npos ) // Find the description
        i++;

      pos1 = gamelist[i].find( "<description>", 0 ) + 13;
      pos2 = gamelist[i].find( "</description>", pos1 ) - pos1;
      string gname = gamelist[i].substr( pos1, pos2 );

      string gyear          = "----";
      string manufacturer   = "";
      string crc            = "";
      string orientation    = "horizontal";
      string players        = "";
      string ctrltype       = "";
      string buttons        = "";
      string joyways        = "";
      bool   emulation_good = false;

      while ( ( is_mame_list && gamelist[i].find( "</machine>",  0 ) == string::npos) ||
              (!is_mame_list && gamelist[i].find( "</software>", 0 ) == string::npos) )
      {
        if ( gamelist[i].find( "<year>", 0 ) != string::npos )
        {
          pos1 = gamelist[i].find( "<year>", 0 ) + 6;
          pos2 = gamelist[i].find( "</year>", pos1 ) - pos1;
          gyear = gamelist[i].substr( pos1, pos2 );
        }
        if ( gamelist[i].find( "<manufacturer>", 0 ) != string::npos )
        {
          pos1         = gamelist[i].find( "<manufacturer>", 0 ) + 14;
          pos2         = gamelist[i].find( "</manufacturer>", pos1 ) - pos1;
          manufacturer = gamelist[i].substr( pos1, pos2 );
          // Remove ?s
          while ( manufacturer.find( "?", 0 ) != string::npos )
          {
            string::size_type pos1 = manufacturer.find( "?", 0 );
            manufacturer.erase( pos1, 1 );
          }
        }
        if ( gamelist[i].find( "<publisher>", 0 ) != string::npos )
        {
          pos1         = gamelist[i].find( "<publisher>", 0 ) + 11;
          pos2         = gamelist[i].find( "</publisher>", pos1 ) - pos1;
          manufacturer = gamelist[i].substr( pos1, pos2 );
        }
        if ( gamelist[i].find( "crc", 0 ) != string::npos )
        {
          pos1         = gamelist[i].find( "crc=\"", 0 ) + 5;
          pos2         = gamelist[i].find( "\"", pos1 ) - pos1;
          crc          = gamelist[i].substr( pos1, pos2 );
        }
        if ( gamelist[i].find( "<input players=\"", 0 ) != string::npos )
        {
          pos1         = gamelist[i].find( "<input players=\"", 0 ) + 16;
          pos2         = gamelist[i].find( "\"", pos1 ) - pos1;
          players      = gamelist[i].substr( pos1, pos2 );
        }
        if ( gamelist[i].find( " buttons=\"", 0 ) != string::npos )
        {
          pos1         = gamelist[i].find( " buttons=\"", 0 ) + 10;
          pos2         = gamelist[i].find( "\"", pos1 ) - pos1;
          buttons      = gamelist[i].substr( pos1, pos2 );
        }
        if ( gamelist[i].find( " ways=\"", 0 ) != string::npos )
        {
          pos1         = gamelist[i].find( " ways=\"", 0 ) + 7;
          pos2         = gamelist[i].find( "\"", pos1 ) - pos1;
          joyways      = gamelist[i].substr( pos1, pos2 );
        }
        if ( gamelist[i].find( "control type=\"", 0 ) != string::npos )
        {
          pos1         = gamelist[i].find( "control type=\"", 0 ) + 14;
          pos2         = gamelist[i].find( "\"", pos1 ) - pos1;
          ctrltype     = gamelist[i].substr( pos1, pos2 );
          if ( ctrltype == "joy" )
            ctrltype = "joystick";
          if ( ctrltype == "only_buttons" )
            ctrltype = "buttons";
          ctrltype[0] = toupper( ctrltype[0] );
        }
        if ( gamelist[i].find( "emulation=\"good\"", 0 )            != string::npos &&
            (gamelist[i].find( "driver status=\"preliminary\"", 0 ) == string::npos ||
             (gamelist[i].find( "color=\"good\"", 0 )               != string::npos &&
              gamelist[i].find( "sound=\"good\"", 0 )               != string::npos)))
        {
          emulation_good = true;
        }
        if ( gamelist[i].find( "rotate=\"90\"",  0 ) != string::npos ||
             gamelist[i].find( "rotate=\"270\"", 0 ) != string::npos )
        {
          orientation = "vertical";
        }
        i++;
      }
     
      // Generate new game, and add it to the list
      game *new_game = new game( gname, gfile, gyear, gclone_of );
      new_game->manufacturer = manufacturer;
      new_game->crc          = crc;
      new_game->orientation  = orientation;
      new_game->players      = players;
      new_game->ctrltype     = ctrltype;
      new_game->buttons      = buttons;
      new_game->joyways      = joyways;
      new_game->working      = emulation_good || !is_mame_list;

      nr_mame_games[index]++;
      if ( mame_games[index] == NULL )
        mame_games[index] = new_game;
      else
      {
        (*new_game).prev          = mame_games[index];
        (*mame_games[index]).next = new_game;
        mame_games[index]         = new_game;
      }

    }
  }

  return;

}


// Write a game list to XML files
void write_gamelist( const string listname, game *gamelist, const int filter, string type = "", const int from = -1, const int to = -1, string genre = "", bool find_clones = false )
{

  // Skip if both c_horizontal and c_vertical flags are set; that's handled by the normal set
  if (( filter & c_horizontal ) && ( filter & c_vertical ))
    return;

  string filename   = "XML/";
  string genre_name = genre;

  string::size_type pos = genre_name.find( "/" );
  if ( pos != string::npos )
    genre_name.replace( pos, 1, "_" );
  pos = genre_name.find( "&amp;" );
  if ( pos != string::npos )
    genre_name.replace( pos, 5, "&" );
  

  if ( type != "")
    filename += type + "/";
  if ( genre != "" )
    filename += "Genre/";
  filename += listname;
  if ( filter & c_horizontal )
    filename += "_horizontal";
  if ( filter & c_vertical )
    filename += "_vertical";
  if ( genre != "" )
    filename += "_" + genre_name;
  if (( filter && filter != c_non_working ) &&
      ( filter && filter != c_horizontal  ) &&
      ( filter && filter != c_vertical    ))
    filename += "_no";
  if ( filter & c_no_casino )
    filename += "_casino";
  if ( filter & c_no_clones )
    filename += "_clones";
  if ( filter & c_no_mahjong )
    filename += "_mahjong";
  if ( filter & c_no_mature )
    filename += "_mature";
  if ( filter & c_no_quiz )
    filename += "_quiz";
  if ( filter & c_no_electromechanical )
    filename += "_electro";
  if ( filter & c_no_utilities )
    filename += "_utilities";
  if ( filter & c_no_playchoice )
    filename += "_playchoice";
  if ( filter & c_non_working )
    filename += "_full";
  if ( type == "players" )
  {
    if ( from != to )
      filename += "_" + to_string(from) + "_" + to_string(to) + "_players";
    else if ( to == 1 )
      filename += "_" + to_string(from) + "_player";
    else
      filename += "_" + to_string(from) + "_players";
  }
  if ( type == "buttons" )
  {
    if ( from != to )
      filename += "_" + to_string(from) + "_" + to_string(to) + "_buttons";
    else if ( to == 1 )
      filename += "_" + to_string(from) + "_button";
    else
      filename += "_" + to_string(from) + "_buttons";
  }
  if ( find_clones )
    filename += "_matching_clones";
  
  filename += ".xml";


  cout << "Writing game list " << filename << endl;
  ofstream file( filename.c_str( ) );
  game *loop_game = gamelist;

  file << "<?xml version=\"1.0\"?>" << endl;
  file << "<menu>"                  << endl;

  string first_letter = "-";

  do
  {

    game *print_game = loop_game;

    if (( filter & c_no_casino ) &&
        ( loop_game->genre.find( "Casino", 0 ) != string::npos ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_no_clones ) &&
        ( loop_game->clone_of != "" ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_no_mahjong ) &&
        ( loop_game->genre.find( "Mahjong", 0 ) != string::npos ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_no_mature ) &&
        (( loop_game->genre.find(  "Mature", 0 ) != string::npos ) ||
         ( loop_game->rating.find( "Sexual", 0 ) != string::npos )))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_no_quiz ) &&
        ( loop_game->genre.find( "Quiz", 0 ) != string::npos ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_no_electromechanical ) &&
        ( loop_game->genre.find( "Electromechanical", 0 ) != string::npos ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_no_utilities ) &&
        ( loop_game->genre.find( "Utilities", 0 ) != string::npos ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_no_playchoice ) &&
        ( loop_game->name.find( "PlayChoice", 0 ) != string::npos ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_horizontal ) &&
        ( loop_game->orientation == "vertical" ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_vertical ) &&
        ( loop_game->orientation == "horizontal" ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if ( genre != "" && loop_game->genre != genre )
    {
      loop_game = loop_game->next;
      continue;
    }

    int number;
    istringstream ( loop_game->players ) >> number;
    if ( type == "players" &&
         (number < from || number > to ))
    {
      if ( find_clones )
      {
         print_game = gamelist;
         do
         {
           print_game = print_game->next;
           istringstream ( print_game->players ) >> number;
           if ( print_game->clone_of == loop_game->file &&
                number               >= from            &&
                number               <= to )
             break;
         }
         while ( print_game != gamelist );
      }
      if ( !( find_clones                             &&
              print_game->clone_of == loop_game->file &&
              number               >= from            &&
              number               <= to ) )
      {
        loop_game = loop_game->next;
        continue;
      }
    }

    istringstream ( loop_game->buttons ) >> number;
    if ( type == "buttons" &&
         (number < from || number > to ))
    {
      loop_game = loop_game->next;
      continue;
    }

    if (( filter & c_non_working ) || print_game->working )
    {
      if ( first_letter != (*print_game).name.substr( 0, 1 ) )
        file << "  <game name=\""  << print_game->file         << "\" index=\"true\" image=\"" << print_game->name.substr( 0, 1 ) << "\">" << endl;
      else
        file << "  <game name=\""  << print_game->file         << "\" index=\"\" image=\"\">"  << endl;
      first_letter = (*print_game).name.substr( 0, 1 );
      file << "    <description>"  << print_game->name         << "</description>"             << endl;
      file << "    <cloneof>"      << print_game->clone_of     << "</cloneof>"                 << endl;
      file << "    <crc>"          << print_game->crc          << "</crc>"                     << endl;
      file << "    <manufacturer>" << print_game->manufacturer << "</manufacturer>"            << endl;
      file << "    <year>"         << print_game->year         << "</year>"                    << endl;
      file << "    <genre>"        << print_game->genre        << "</genre>"                   << endl;
      file << "    <rating>"       << print_game->rating       << "</rating>"                  << endl;
      if ( print_game->players != "" )
        file << "    <players>"    << print_game->players      << "</players>"                 << endl;
      if ( print_game->ctrltype != "" )
        file << "    <ctrltype>"   << print_game->ctrltype     << "</ctrltype>"                << endl;
      if ( print_game->buttons != "" )
        file << "    <buttons>"    << print_game->buttons      << "</buttons>"                 << endl;
      if ( print_game->joyways != "" )
        file << "    <joyways>"    << print_game->joyways      << "</joyways>"                 << endl;
      file << "    <score>"        << print_game->score        << "</score>"                 << endl;
      file << "    <enabled>"      << "Yes"                    << "</enabled>"                 << endl;
      file << "  </game>"                                                                     << endl;
    }

    loop_game = loop_game->next;

  }
  while ( loop_game != gamelist );

  file << "</menu>" << endl;
  file.close( );

  return;

}


// Write the MAME game lists to XML files
void write_mame_games( )
{

  string         first_letter;
  vector<string> genres;

  mkdir( "XML", 0755 );
  mkdir( "XML/Genre", 0755 );
  mkdir( "XML/players", 0755 );
  mkdir( "XML/buttons", 0755 );

  game *loop_game = mame_games[0];
  do
  {
    bool found = false;
    for ( string genre : genres )
      if ( loop_game->genre == genre )
        found = true;
    if ( !found )
      genres.push_back( loop_game->genre );
    loop_game = loop_game->next;
  }
  while ( loop_game != mame_games[0] );
  std::sort( genres.begin( ), genres.end( ) );

  for ( uint index = 0; index < mame_systems.size( ); index++ )
  {

    // Only write non-empty lists
    if ( nr_mame_games[index] == 0 || mame_games[index] == NULL )
    {
      cout << "No games available for system " << mame_systems[index] << ". No file created." << endl;
      continue;
    }

    if ( index == 0 ) // MAME list
    {
      for ( int i = 0; i <= c_non_working; i++ )
        write_gamelist( mame_systems[index], mame_games[index], i );
      for ( string genre : genres )
      {
        write_gamelist( mame_systems[index], mame_games[index], c_none, "", -1, -1, genre );
        write_gamelist( mame_systems[index], mame_games[index], c_no_clones, "", -1, -1, genre );
        write_gamelist( mame_systems[index], mame_games[index], c_none|c_vertical, "", -1, -1, genre );
        write_gamelist( mame_systems[index], mame_games[index], c_no_clones|c_vertical, "", -1, -1, genre );
        write_gamelist( mame_systems[index], mame_games[index], c_none|c_horizontal, "", -1, -1, genre );
        write_gamelist( mame_systems[index], mame_games[index], c_no_clones|c_horizontal, "", -1, -1, genre );
      }
      for ( int from = 1; from <= 8; from++ )
      {
        for ( int to = from; to <= 8; to++ )
        {
          write_gamelist( mame_systems[index], mame_games[index], c_none, "players", from, to );
          write_gamelist( mame_systems[index], mame_games[index], c_no_clones, "players", from, to );
          write_gamelist( mame_systems[index], mame_games[index], c_none, "buttons", from, to );
          write_gamelist( mame_systems[index], mame_games[index], c_no_clones, "buttons", from, to );
        }
      }
      write_gamelist( mame_systems[index], mame_games[index], c_no_casino|c_no_clones|c_no_mahjong|c_no_mature|c_no_quiz|c_no_electromechanical|c_no_utilities|c_no_playchoice, "players", 1, 2, "", true );
    }
    else // Software list
    {
      write_gamelist( mame_systems[index], mame_games[index], c_none );
      write_gamelist( mame_systems[index], mame_games[index], c_no_clones );
    }

  }

  // Generate genre.xml

  cout << "Generating a genre.xml file." << endl;

  ofstream file( "XML/Genre/genre.xml" );

  file << "<?xml version=\"1.0\"?>" << endl;
  file << "<menu>"                  << endl;
  file << "  <game name=\"All Games\"/>" << endl;
  for ( string genre : genres )
    if ( genre != "" )
      file << "  <game name=\"" << genre << "\"/>" << endl;
  file << "</menu>" << endl;
  file.close( );

  return;

}


int main()
{

  create_mame_games( ); // Read arcade games
  create_mame_games( ); // Read softwarelist games

  // Sort the lists
  //
  for ( uint index = 0; index < mame_systems.size( ); index++ )
  {
    if ( nr_mame_games[index] != 0 && mame_games[index] != NULL )
    {
      // Set the pointer to the start of the list, and sort the list
      while ( (*mame_games[index]).prev != NULL )
        mame_games[index] = (*mame_games[index]).prev;
      cout << "Sorting list " << mame_systems[index] << "." << endl;
      sort( mame_games[index], nr_mame_games[index] );
      // Find the start of the list
      while ( (*(*mame_games[index]).prev).name < (*mame_games[index]).name )
        mame_games[index] = (*mame_games[index]).prev;
    }
  }

  read_extra_info( );
  write_mame_games( );
  return 0;
}
