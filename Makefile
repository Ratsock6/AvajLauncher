# =========================
# Avaj Launcher - Makefile
# =========================

# --- Configurable vars ---
JAVAC      = javac
JAVA       = java
FIND       = find

SRC_DIR    = src
OUT_DIR    = out
SRC_LIST   = sources.txt

# Classe principale (package complet)
MAIN_CLASS = fr.aallouv.avajlauncher.AvajLauncher

# Fichier scénario (modifiable à l'appel)
SCENARIO   = scenario.txt

# --- Phony targets ---
.PHONY: all compile run clean fclean re help check

# --- Default ---
all: compile

# --- Generate sources.txt with all .java files ---
$(SRC_LIST):
	@$(FIND) $(SRC_DIR) -name "*.java" > $(SRC_LIST)
	@echo "📝 Généré: $(SRC_LIST)"

# --- Ensure out dir exists ---
$(OUT_DIR):
	@mkdir -p $(OUT_DIR)

# --- Compile all sources into out/ ---
compile: $(SRC_LIST) | $(OUT_DIR)
	@echo "☕ Compilation en cours..."
	@$(JAVAC) @$(SRC_LIST) -d $(OUT_DIR)
	@echo "✅ Compilation terminée -> $(OUT_DIR)"

# --- Run the simulator ---
run: compile
	@echo "🚀 Exécution avec le scénario: $(SCENARIO)"
	@$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) $(SCENARIO)

# --- Clean compiled classes (keeps sources.txt & scenario/output files) ---
clean:
	@echo "🧹 Nettoyage des classes compilées..."
	@rm -rf $(OUT_DIR)

# --- Full clean (also removes sources.txt & simulation.txt) ---
fclean: clean
	@echo "🧽 Nettoyage complet (sources.txt + simulation.txt)..."
	@rm -f $(SRC_LIST) simulation.txt
	@$(FIND) . -name "*.class" -type f -delete

# --- Rebuild from scratch ---
re: fclean all

# --- Quick environment check ---
check:
	@echo "🔎 Vérification Java:"
	@$(JAVAC) -version
	@$(JAVA) -version
	@echo "SRC_DIR=$(SRC_DIR)"
	@echo "OUT_DIR=$(OUT_DIR)"
	@echo "MAIN_CLASS=$(MAIN_CLASS)"
	@echo "SCENARIO=$(SCENARIO)"

# --- Help message ---
help:
	@echo "== Avaj Launcher Makefile =="
	@echo "make            : compile"
	@echo "make run        : compile puis lance le programme"
	@echo "                 (par défaut avec SCENARIO=$(SCENARIO))"
	@echo "make clean      : supprime le dossier $(OUT_DIR)"
	@echo "make fclean     : clean + supprime sources.txt et simulation.txt"
	@echo "make re         : fclean puis compile"
	@echo "make check      : affiche les versions Java et les chemins"
	@echo
	@echo "Exemples:"
	@echo "  make run"
	@echo "  make run SCENARIO=tests/scenario_ok.txt"