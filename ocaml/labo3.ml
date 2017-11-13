let reverse = it_list (fun a h -> h::a) [];;

let rec it_list f a = function
    [] -> a
  | h::t -> it_list f (f a h) t;;

let cat_all = it_list ( ^ ) "";;

let map f =  it_list (fun a h -> (f h)::a) [];;

let filter p = it_list (fun a h -> if p h then h::a else a) [];;

type shape = Square of float | Circle of float | Rectangle of float* float;;

let perimeter s = match s with
    Square l -> l *. 4.
  | Circle l -> 2. *. l *. acos(-1.)
  | Rectangle (l,l0) -> 2. *. l +. 2. *. l0;;

let area s = match s with
    Square l -> l *. l
  | Circle l -> l *. l *. acos(-1.)
  | Rectangle (l,l0) -> l *. l0;;

