grammar xrobots:abstractsyntax;

import xrobots:terminals;

synthesized attribute pp               :: String  ;
synthesized attribute name             :: Id_t    ;
synthesized attribute htrans           :: String  ;
synthesized attribute errors           ::[String] ;
synthesized attribute type             :: Type    ;
synthesized attribute rows             :: Integer ;
synthesized attribute cols             :: Integer ;
synthesized attribute is_equal         :: Boolean ;
synthesized attribute arrayElementType :: Type    ;
synthesized attribute paramTypes       :: [Type]  ;
synthesized attribute noOfStaticLinks  :: Integer ;
synthesized attribute frameSyn         :: Frame   ;
synthesized attribute decHTrans        :: String  ;
synthesized attribute bindingHTrans    :: String  ;
synthesized attribute childBehaviors   :: String  ;
synthesized attribute subBehaviorDecs  :: DecList ;


inherited attribute check_for_equal    :: Type    ;
inherited attribute frameIn            :: Frame   ;
inherited attribute stack              :: Stack   ;
inherited attribute haskellStack       :: String  ;
inherited attribute haskellBindings    :: String  ;
inherited attribute nameIn             :: Id_t    ;
inherited attribute tabs               :: String  ;
inherited attribute subBehaviorDecsIn  :: DecList   ;