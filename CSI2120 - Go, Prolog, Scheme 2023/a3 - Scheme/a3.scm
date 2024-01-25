(define sudoku1 '((2 1 4 3) (4 3 2 1) (1 2 3 4) (3 4 1 2)))
(define sudoku2 '((2 1 4 3) (4 3 2 1) (1 2 3 3) (3 4 1 2)))


(define (different L)
(if (list? L)
(different2 L)
'list-error))
(define (different2 L)
(cond
((null? L) #t)
((member (car L) (cdr L)) #f)
(else 
(different2 (cdr L))))
)
;;(display (different '(1 2 3 4 5 6 7)))



(define (extract4Columns L)
    (car(mcol L 0)))
    
(define (mcol m n)
  (let iter ((i (length m)) (result '()))
    (if (= i 0)
        result
        (iter (- i 1)
              (cons m '())))))

;;(display(extract4Columns sudoku1))

(define (extract4Quadrants L)
    (car(mcol L 0)))

(define (mquad m n)
  (let iter ((i (/(length m) 2)) (result '()))
    (if (= i 0)
        result
        (iter (- i 1)
              (cons m '())))))
          
          
;;(display (extract4Quadrants sudoku1))

(define (merge3 a b c)
    (merge (merge a b) c))

(define (merge L1 L2)
    (if (null? L1)
        L2
    (cons (car L1) (merge (cdr L1) L2))))

;;(display(merge3 sudoku2 (extract4Columns sudoku2) (extract4Quadrants sudoku2)))

(define (checkSudoku sudoku)
    (not(member #f (map different(merge3 sudoku (extract4Columns sudoku) (extract4Quadrants sudoku))))))

;;(display(checkSudoku sudoku1))
