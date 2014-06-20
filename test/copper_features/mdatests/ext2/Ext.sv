grammar copper_features:mdatests:ext2;

import copper_features:mdatests:host;

-- SAME token as :ext

marking terminal C 'c';

terminal E 'e'; -- prefix

concrete production ccc222
top::Root ::= 'c' Root
{}

