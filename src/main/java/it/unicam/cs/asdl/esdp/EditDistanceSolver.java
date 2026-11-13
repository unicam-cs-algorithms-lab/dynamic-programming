package it.unicam.cs.asdl.esdp;
 import java.util.Arrays;
 /**
  * Distanza di Levenshtein tra due stringhe con costo 1 per inserimento,
  * cancellazione o sostituzione, e costo 0 per un match.
  *
  * <p><b>Definizione del sottoproblema:</b> {@code memo[i][j]} è il minimo
  * numero di operazioni per trasformare il prefisso {@code x[0..i)} nel
  * prefisso {@code y[0..j)}. La riga e la colonna 0 rappresentano la stringa
  * vuota. Per i,j positivi si considera il minimo tra:
  * <ul><li>{@code D(i-1,j)+1}: cancellazione dell'ultimo carattere di x;
  * <li>{@code D(i,j-1)+1}: inserimento dell'ultimo carattere di y;
  * <li>{@code D(i-1,j-1)+(x[i-1]==y[j-1] ? 0 : 1)}: match o sostituzione.</ul>
  * <p>Si richiede il calcolo TOP-DOWN: prima verificare la cache,
  * poi risolvere ricorsivamente i sottoproblemi e infine registrare il valore.
  * Numero di stati O(nm), lavoro per stato O(1): tempo O(nm), spazio O(nm).
  * La ricorsione richiede anche stack O(n+m). I valori memorizzati sono >= 0:
  * perciò -1 è una sentinella sicura per una cella ancora ignota.
  *
  * <p>Nel traceback, partendo da (n,m), si risale fino a (0,0).
  * In caso di pareggio si preferisce diagonale, poi cancellazione,
  * poi inserimento: questa convenzione garantisce un risultato deterministico,
  * ma possono esistere altri allineamenti altrettanto ottimi.
  */
 public class EditDistanceSolver {
     private final String x,y;
     private final int[][] memo;
     private boolean solved;
     /**
      * Inizializza un nuovo risolutore per la coppia di stringhe.
      * @param x stringa di partenza
      * @param y stringa di arrivo
      * @throws NullPointerException se una stringa è null
      * @throws IllegalArgumentException se contiene il simbolo riservato '-' */
     public EditDistanceSolver(String x,String y) {
         if(x==null||y==null) throw new NullPointerException("stringhe null");
         if(x.indexOf('-')>=0||y.indexOf('-')>=0) throw new IllegalArgumentException("'-' riservato ai gap");
         this.x=x;this.y=y;
         this.memo=new int[x.length()+1][y.length()+1];
         for(int[] row:memo) Arrays.fill(row,-1);
     }
     /** Esegue il calcolo, una sola volta. È lecito richiamarlo più volte. */
     public void solve() { if(!solved) {distance(x.length(),y.length());solved=true;} }
     /**
      * Restituisce un valore calcolato oppure uno stato del risolutore.
      * @return true se solve è già stato invocato con successo */
     public boolean isSolved() {return solved;}
     /**
      * Restituisce un valore calcolato oppure uno stato del risolutore.
      * @return numero minimo di operazioni
      * @throws IllegalStateException se non è stato chiamato solve */
     public int getDistance() {requireSolved();return memo[x.length()][y.length()];}
     /**
      * Restituisce un valore calcolato oppure uno stato del risolutore.
      * @return un allineamento globale che realizza la distanza minima
      * @throws IllegalStateException se solve non è stato chiamato */
     public AlignmentResult getOneOptimalAlignment() {
         requireSolved();StringBuilder a=new StringBuilder(),b=new StringBuilder();
         traceBack(x.length(),y.length(),a,b);
         return new AlignmentResult(a.reverse().toString(),b.reverse().toString(),getDistance());
     }
     private void requireSolved() {if(!solved)throw new IllegalStateException("chiamare solve prima");}
     /**
      * TODO 1 (obbligatorio). Calcolare D(i,j) ricorsivamente con memoizzazione.
      * I casi base sono D(0,j)=j e D(i,0)=i. IMPORTANTE: controllare
      * memo[i][j] PRIMA di eseguire nuove chiamate ricorsive; salvare il
      * risultato anche per i casi base. Non riempire tutta la matrice con cicli.
      * @param i lunghezza del prefisso di x, tra 0 e x.length()
      * @param j lunghezza del prefisso di y, tra 0 e y.length()
      * @return minimo numero di operazioni per i due prefissi
      */
     private int distance(int i,int j) {
         // TODO: memoizzazione top-down della ricorrenza illustrata nella Javadoc.
         throw new UnsupportedOperationException("TODO distance");
     }
     /**
      * TODO 2 (obbligatorio). Costruire all'indietro le due righe allineate.
      * Un passo diagonale aggiunge x[i-1] e y[j-1] (match/sostituzione);
      * un passo verso l'alto aggiunge x[i-1] e '-'; un passo a sinistra
      * aggiunge '-' e y[j-1]. Scegliere solo predecessori coerenti con
      * il costo ottimo. Ricordare i bordi i=0 o j=0. I StringBuilder
      * vengono invertiti dal metodo pubblico: qui si accoda dalla fine.
      * @param i prefisso residuo di x @param j prefisso residuo di y
      * @param a prima riga al contrario @param b seconda riga al contrario
      */
     private void traceBack(int i,int j,StringBuilder a,StringBuilder b) {
         // TODO: ricostruire una soluzione ottima, non soltanto il valore.
         throw new UnsupportedOperationException("TODO traceback");
     }
 }