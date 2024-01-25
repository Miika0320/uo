;;Mikaela Dobie 300164161 p3

;;reads file in
(define (readXYZ fileIn)
    (let ((sL (map (lambda s (string-split (car s)))
                        (cdr (file->lines fileIn)))))
        (map (lambda (L)
            (map (lambda (s)
                (if (eqv? (string->number s) #f)
                    s
                    (string->number s))) L)) sL)))
                
;;overall uses other functions to find dominant plane from file pointclouds				
(define (planeRANSAC filename confidence percentage eps)
    (let ((Ps (readxyz filename))(k (ransacNumberOfIteration confidence percentage))(plane (dominantPlane Ps k))))
    (call-with-output-file "output.txt"
        (lambda (output-port)
        (display plane output-port))))

;;creates plane from three points
(define(plane p1 p2 p3)
    (let ((line1 '((- car(p1) car(p2)), (- car(cdr(p1)) car(cdr(p2))), (- cdr(cdr(p1)) cdr(cdr(p2)))))
         (line2 '((- car(p3) car(p2)), (- car(cdr(p3)) car(cdr(p2))), (- cdr(cdr(p3)) cdr(cdr(p2)))))
         (i  (-(* car(cdr(line1)) cdr(cdr(line2))) (* cdr(cdr(line1)) car(cdr(line2)))))
         (j  (-(-(* car(line1) cdr(cdr(line2))) (* cdr(cdr(line1)) car(line2)))))
         (k  (-(* car(line1) car(cdr(line2))) (* car(cdr(line1)) car(line2))))
         (d  (+ (+(* car(p1) i)(* car(cdr(p1)) j))(* cdr(cdr(p1)) k))))
         (cons i(cons j(cons k(cons d '()))))))
     
;;calculates number of iterations to find dominant plane
(define(ransacNumberOfIteration confidence percentage)
        (/ log(confidence) log((expt percentage 3))))
    
;;determines dominant plane by testing support
(define (dominantPlane Ps k)
    (helper Ps k -1))

;;checks if point exists in plane
(define (inPlane plane pt)
    (=(+(+(* ((list-ref plane 0) (list-ref pt 0))
                 (* (list-ref plane 1) (list-ref pt 1))))
             (* (list-ref plane 2) (list-ref pt 2))) (list-ref plane 3)))

;;determines pair (numPointsinPlane: Plane)
(define(support plane points)
    (let ((n 0))
    (count plane points n)))
    
;;counts the points in a plane
(define(count plane points n)
    (cond(= (points) '()) (cons(n plane))
    ((inPlane plane car(points)) (count plane cdr(points) (+ n 1)))
    (else (count plane cdr(points) n))))
            
;; calulates support k times as determined by iterations function
(define (helper Ps try best)
(let ((plane (list-ref Ps (random (length Ps))))) (x (support plane Ps)))
(cond
 ((= 0 try) cdr(best) )
 ((> car(x) car(best)) (helper Ps (- try 1) (x)))
 (else (helper Ps (- try 1) best))))
            
            
            
            
            
            
            
            
            