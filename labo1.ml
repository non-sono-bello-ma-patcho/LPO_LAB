(*esercizio 1*)
let rec fact n = if n=0 then 1 else n*fact (n-1);;

(*esercizio 2*)
let rec pow b e = if e <=0 then 1 else b*pow b (e-1);;

(*esercizio 3*)
let rec eq f1 f2 (a,b) = 
  if b<a then true
  else if f1(a) = f2(a) then eq f1 f2 (a, b-1)
  else false;;

(*esercizio 44*)
let rec insert e l =
  match l with
      [] -> [e] (*se e' la lista vuota, resituisce la lista con e*)
    | h::t -> if e = h then h::t else h::insert e t;;
(*se il primo elemento della lista è 'e' allora restituisce la lista l altrimenti restituisce il risultato dell'espressione h::insert e t h:insert e t viene visto come concatena alla testa il risultato di insert sulla coda, questo vuol dire che farà l'append di ogni singolo elemento finché non arriva lla lista vuota e quindi aggiunge e in coda. Altrimenti salta e ed aggiunge solo la coda*)

(*esercizio 5*)
let rec reverse l=
  match l with
      [] -> [] (*se è la coda vuota restituisce coda vuota*)
    | h::t -> reverse t @ [h] ;; (*altrimenti concatena la coda con la testa*)

(*esercizio 6*)
let rec delete e l = 
  match l with 
      [] -> []
    |h::t -> if h=e then delete e t else h::delete e t;;

(*fin'ora ho capito che se non specifico la lista come parametro devo usare function, altrimenti devo usare match l with (perché se non è specificata la riconosce automaticamente?)*)

(*esercizio 7*)
let rec ord_member e l = 
  match l with 
      []->false (*sono arrivato in fondo e non ho trovato nulla*)
    |h::t-> if e<h then false (*dalla natura della lista ordinata se arrivo qui senza trovare nulla allora l'elemento non sara' presente*)
        else if e>h then ord_member e t(*chiamata ricorsiva sulla coda*)
        else true;;(*h=e*)

(*esercizio 8*)
let rec ord_insert e l=
  match l with 
      []->[]
    | h::t -> if h=e then h::t
        else if h>e then e::h::t
        else h::ord_insert e t;;

(*esercizio 9*)
let rec ord_delete e l=
  match l with
    []->[]
   | h::t -> if h=e then t
      else if h>e then h::ord_delete e t
      else h::t;;
