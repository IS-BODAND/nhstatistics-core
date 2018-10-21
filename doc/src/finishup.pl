#!/usr/bin/perl
use v5.10;
use warnings FATAL => 'all';
use File::Copy qw/copy/;
no warnings qw/uninitialized/;

my $docsDir = "../../docs";
my $cssFile = "$docsDir/static/css/main.css";
my $htmlFile = "$docsDir/index.html";

# In-place editing with ~ backup file
$^I = "~";

sub break_lines {
    my ($file, $sep) = @_;
    $sep = "}" unless defined $sep;
    `perl -i$^I -pe "s!\Q$sep\E!$sep\\n!g" $file`
}

sub merge_lines {
    my $file = shift;
    `perl -i -pe "s/\\n+//g" $file`
}

sub handle_css {
    sub property_writer {
        my ($css, $ref_properties) = @_;
        my %properties = %$ref_properties;
        foreach my $selector (keys %properties) {
            print $css "$selector {";
            foreach my $property (keys %{$properties{$selector}}) {
                print $css "$property:$properties{$selector}{$property};"
            }
            print $css '}'
        }
    }

    my $properties = {
        '.removed-bg-code'                   => {
            'background-color' => '#474A4C',
            'font-size'        => '1.2em'
        },
        '.bodiv'                             => {
            width  => '100%!important',
            height => '100%!important'
        },
        '.body'                              => {
            padding => '0 6%',
            margin  => '0 0 0 280px'
        },
        '.header'                            => {
            color => '#1E2021'
        },
        '.menu-scbar'                        => {
            width  => '100%!important',
            height => '84%!important'
        },
        '.tse-scroll-content'                => {
            height => '100%',
        },
        '.menu-long-logo'                    => {
            height => '15%'
        },
        '.bodiv>.tse-scrollbar>.drag-handle' => {
            display => 'none'
        }
    };

    break_lines $cssFile;
    open my $css, ">>", $cssFile;
    property_writer $css, $properties;
    close $css;
    @ARGV = ($cssFile);
    while (<ARGV>) {
        print s/\Q.sidebar-content{margin-top:4rem}\E/.sidebar-content{height:100%}/r;
    }
    merge_lines $cssFile;
}

sub handle_html {
    sub scrollbars {
        sub print_bonus_language {
            print qq{    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/languages/gradle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/languages/clojure.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/languages/scala.min.js"></script>\n}
        }
        sub print_scrollbar_styling_headers {
            print qq{    <link rel="stylesheet" href="static/css/trackpad-scroll-emulator.css" />\n}
        }
        sub print_menu_scrollbar_styling_begin {
            print <<'HTML'
<div class="tse-scrollable menu-scbar">
    <div class="tse-content">
HTML
        }
        sub print_main_scrollbar_styling_begin {
            print <<'HTML'
<div class="tse-scrollable bodiv">
    <div class="tse-content">
HTML
        }
        sub print_scrollbar_styling_end {
            print qq{        </div>\n    </div>\n}
        }
        sub print_scrollbar_js {
            print <<'HTML'
<script src="static/js/jquery-1.7.1.min.js"></script>
<script src="static/js/jquery.trackpad-scroll-emulator.min.js"></script>
<script>
    $(document).ready(function() {
        $('.menu-scbar').TrackpadScrollEmulator();
        $('.bodiv').TrackpadScrollEmulator();
    })
</script>
HTML

        }
        sub print_scrollbar_fallback {
            print qq{<noscript>
  <style>
    .tse-scrollable {
      overflow-y: scroll;
    }
    .tse-scrollable.horizontal {
      overflow-x: scroll;
      overflow-y: hidden;
    }
  </style>
</noscript>\n}
        }

        print_bonus_language if $. == 15;
        print_scrollbar_styling_headers if $. == 16;
        print_scrollbar_fallback if $. == 17;
        print_menu_scrollbar_styling_begin if $. == 65;
        print_scrollbar_styling_end if $. == 96 || $. == 562;
        print_main_scrollbar_styling_begin if $. == 98;
        print_scrollbar_js if $. == 564
    }

    @ARGV = ($htmlFile);
    while (<ARGV>) {
        s/<code>/<code class="removed-bg-code">/g;
        s/<h(\d)>/<h$1 class="header">/g;
        s/<div class="container is-fluid">/<div class="container is-fluid menu-long-logo">/g;
        print;
        scrollbars;
    }
}

sub copy_bonus_scrollbar_files {
    sub copyFile {
        my ($orig, $type) = @_;
        $type = 'js' unless defined $type;
        my $new = "$docsDir/static/$type/$orig";
        copy $orig, $new;
    }

    # jQuery
    copyFile "jquery-1.7.1.min.js";
    # Scrollbar js
    copyFile "jquery.trackpad-scroll-emulator.min.js";
    # Scrollbar css
    copyFile "trackpad-scroll-emulator.css", "css";
}

handle_css;
handle_html;
copy_bonus_scrollbar_files;
