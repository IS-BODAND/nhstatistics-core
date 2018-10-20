use v5.10;
use utf8;
use strict;
use warnings;
no warnings qw/uninitialized/;

# Get version
open my $versionFile, "<:crlf", "../../../build.gradle"
    or die 'Chaos $!';

my $version = "";
/^version '([.\d]+)'/ && ($version = $1) while (<$versionFile>);
close $versionFile;
say "Version found: $version";

# Get all files
opendir my $directoryOfTemplates, "./templates" or die "Chaos $!";
my @templateFiles = grep {/\w/} readdir $directoryOfTemplates;
close $directoryOfTemplates;

# Get files
my %filled = ();
$filled{"templates/$_"} = s/\A(.+)_template.md\Z/$1.md/r foreach @templateFiles;

# Fill templates
my @templates = keys %filled;
my @filledFiles = values %filled;
for (my $i = 0; $i <= $#templates; ++$i) {
    open my $infile, "<:crlf", $templates[$i];
    open my $outfile, ">", $filledFiles[$i];
    print $outfile s/==!version==/$version/gr while (<$infile>);
    close $infile;
    close $outfile;
}
