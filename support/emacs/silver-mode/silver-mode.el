(defvar silver-mode-hook nil)

(add-to-list 'auto-mode-alist '("\\.sv\\'" . silver-mode))

(defun silver-comment-dwim (arg)
"Comment or uncomment current line or region in a smart way but with silver's -- syntax
For detail, see `comment-dwim'."
   (interactive "*P")
   (require 'newcomment)
   (let ((deactivate-mark nil) (comment-start "--") (comment-end ""))
     (comment-dwim arg)))

(defconst silver-font-lock-keywords-1
  (list
   '("[\']\\(\\([\\\\].\\)\\|[^\']\\)*[\']". font-lock-string-face)
   '("[\/]\\(\\([\\\\].\\)\\|[^\/]\\)*[\/]". font-lock-string-face)
   '("\\<\\(production\\|productions\\|occurs\\|on\\|nonterminal\\|attribute\\|if\\|then\\|else\\|export\\|exports\\|close\\|closed\\)\\>" . font-lock-keyword-face)
   '("\\<\\(local\\|terminal\\|concrete\\|abstract\\|ignore\\|start\\|lexer\\|aspect\\|decorate\\|autocopy\\)\\>" . font-lock-keyword-face)
   '("\\<\\(import\\|imports\\|grammar\\|association\\|precedence\\|synthesized\\|inherited\\|with\\|as\\|include\\)\\>" . font-lock-keyword-face)
   '("\\<\\(only\\|hiding\\|using\\|forwards\\|to\\|use\\|syntax\\|forwarding\\|function\\|return\\)\\>" . font-lock-keyword-face)
   '("\\<\\(Integer*\\|Boolean\\|String\\|Float\\|Reference\\|Production\\|Decorated\\)\\>" . font-lock-type-face)
   '("\\<\\(reference\\|length\\|substring\\|indexof\\|parser\\|error\\|cast\\|toInt\\|toReal\\|toString\\|new\\)\\>" . font-lock-builtin-face)
   '("\\<\\(cons\\|null\\|head\\|tail\\|let\\)\\>" . font-lock-builtin-face)
   '("\\<\\(true\\|false\\|left\\|right\\|none\\)\\>" . font-lock-constant-face)
   '("\\<\\(case\\|of\\|end\\)\\>" . font-lock-keyword-face)
   '("\\<grammar\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<import\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<export\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<imports\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<exports\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<as\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<syntax\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<production\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<productions\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<function\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<nonterminal\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<terminal\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-function-name-face)
   '("\\<Reference\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-type-face)
   '("\\<Decorated\\ \\<\\([A-Za-z_0-9:]+\\)\\>" 1 font-lock-type-face)
   '("\\<\\(reference\\)\\>([A-Za-z_0-9:\\ \n\t\r]+" 1 font-lock-builtin-face t)
   '("\\<on[\\ \t]+{?\\([A-Za-z_0-9:,\\ \t]+\\)}?[\\ \t]*;" 1 font-lock-type-face)
   '("\\<with[\\ \t\r\n]+{\\([A-Za-z_0-9:,\\ \t\r\n]+\\)}[\\ \t\r]*\\(;\\|,\\)" 1 font-lock-function-name-face)
   '("\\<only[\\ \t\r\n]+{\\([A-Za-z_0-9:,\\ \t\r\n]+\\)}[\\ \t\r]*\\(;\\|,\\)" 1 font-lock-function-name-face)
   '("\\<hiding[\\ \t\r\n]+{\\([A-Za-z_0-9:,\\ \t\r\n]+\\)}[\\ \t\r]*\\(;\\|,\\)" 1 font-lock-function-name-face)
   '("\\<local\\> attribute\\ \\<\\([A-Z0-9a-z_:]+\\)\\>" 1 font-lock-variable-name-face)
   '("\\<attribute\\ \\<\\([A-Z0-9a-z_:]+\\)\\>" 1 font-lock-function-name-face)
   '("[^:]::[\\ ]*\\<\\([A-Z0-9a-z_:]+\\)\\>" 1 font-lock-type-face)
   '("\\<\\([A-Za-z_0-9]+\\)\\>[\\ ]*::=" 1 font-lock-type-face )
   '("\\<\\([A-Za-z_0-9]+\\)\\>[\\ ]*::[^:]" 1 font-lock-variable-name-face )
   '("\\<local\\ attribute\\ \\<\\([A-Z0-9a-z_:]+\\)\\>" 1 font-lock-variable-name-face)

    ;Haskell comments
   '("--.*$" 0 font-lock-comment-face t)
    )
  "Additional Keywords to highlight in SILVER mode.")


(defvar silver-font-lock-keywords silver-font-lock-keywords-1
  "Default highlighting expressions for SILVER mode.")

(defvar silver-mode-syntax-table
  (let ((silver-mode-syntax-table (make-syntax-table)))
	
        ; This is added so entity names with underscores can be more easily parsed
	(modify-syntax-entry ?_ "w" silver-mode-syntax-table)

        ; Haskell style comments
	(modify-syntax-entry ?{ ". 1" silver-mode-syntax-table)
	(modify-syntax-entry ?} ". 4" silver-mode-syntax-table)
	(modify-syntax-entry ?- ". 23" silver-mode-syntax-table)
;	(modify-syntax-entry ?\n "> b" silver-mode-syntax-table)

;       Comment styles are same as C++
;	(modify-syntax-entry ?/ ". 124b" silver-mode-syntax-table)
;	(modify-syntax-entry ?* ". 23" silver-mode-syntax-table)
;	(modify-syntax-entry ?\n "> b" silver-mode-syntax-table)
	silver-mode-syntax-table)
  "Syntax table for silver-mode")
  
(defun silver-mode ()
  (interactive)
  (kill-all-local-variables)
  (set-syntax-table silver-mode-syntax-table)

  ;; Set up font-lock
  (set (make-local-variable 'font-lock-defaults) '(silver-font-lock-keywords))

  ;; modify key map
;;  (define-key silver-mode-map [remap comment-dwim] 'silver-comment-dwim)

  (setq major-mode 'silver-mode)
  (setq mode-name "SILVER")
  (run-hooks 'silver-mode-hook))

(provide 'silver-mode)

;;; silver-mode.el ends here



