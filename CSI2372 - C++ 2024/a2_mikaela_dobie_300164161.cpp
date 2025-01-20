// ------------------------------------------------------------------------------
// Assignment 2
// Written by: Mikaela Dobie 300164161
// For CSI2372 Section B
// Time needed to complete this assignment: 3 hours
// List the resources used to complete this assignment: Course notes, W3 Schools
// ----------------------------------------------------------------------------- 

#include <iostream>
#include <queue>  
#include <stack>  
#include <cstring> 
using namespace std;

// Node structure for linked list
struct Node {
    int vertex;
    Node* next;
    Node(int v) : vertex(v), next(nullptr) {}
};

// Graph class dsigned adjacency list
class Graph {
protected:
    Node** adjacencyList;  // Array of linked lists
    int vertices;
private:
    // Helper method to clear memory (used in destructor)
    void clearGraph() {
        for (int i = 0; i < vertices; ++i) {
            Node* current = adjacencyList[i];
            while (current) {
                Node* temp = current;
                current = current->next;
                delete temp;
            }
        }
        delete[] adjacencyList;
    }

public:
    // Default Constructor
    Graph() : vertices(0), adjacencyList(nullptr) {}

    // Parameterized Constructor
    Graph(int n) : vertices(n) {
        adjacencyList = new Node*[vertices];
        for (int i = 0; i < vertices; ++i) {
            adjacencyList[i] = nullptr;
        }
    }

    // Copy Constructor
    Graph(const Graph& ancestor) : vertices(ancestor.vertices) {
        adjacencyList = new Node*[vertices];
        for (int i = 0; i < vertices; ++i) {
            Node* original = ancestor.adjacencyList[i];
            Node* copy = nullptr;
            Node** copyPtr = &adjacencyList[i];
            while (original) {
                *copyPtr = new Node(original->vertex);
                copy = *copyPtr;
                original = original->next;
                copyPtr = &copy->next;
            }
        }
    }

    // Destructor
    ~Graph() {
        clearGraph();
    }

    // Overload << operator to print the graph
    friend ostream& operator<<(ostream& os, const Graph& g) {
        os << "V = {";
        for (int i = 0; i < g.vertices; ++i) {
            if (i > 0) os << ", ";
            os << i;
        }
        os << "}\nE = {\n";
        for (int i = 0; i < g.vertices; ++i) {
            os << "  " << i << " => ";
            Node* current = g.adjacencyList[i];
            if (!current) os << "None";
            while (current) {
                os << current->vertex;
                if (current->next) os << ", ";
                current = current->next;
            }
            os << "\n";
        }
        os << "}\n";
        return os;
    }

    // Add an edge from vertexA to vertexB
    virtual void add_edge(int vertexA, int vertexB) {
        if (vertexA < 0 || vertexA >= vertices || vertexB < 0 || vertexB >= vertices) {
        cerr << "One or more of the vertices are INVALID!" << endl;
        return;
        }
        // Add edge in both directions for undirected graph
        Node* newNodeA = new Node(vertexB);
        newNodeA->next = adjacencyList[vertexA];
        adjacencyList[vertexA] = newNodeA;

        Node* newNodeB = new Node(vertexA);
        newNodeB->next = adjacencyList[vertexB];
        adjacencyList[vertexB] = newNodeB;
    }

    // Remove an edge from vertexA to vertexB
    void remove_edge(int vertexA, int vertexB) {
        if (vertexA < 0 || vertexA >= vertices) return;
        Node* current = adjacencyList[vertexA];
        Node* prev = nullptr;
        while (current && current->vertex != vertexB) {
            prev = current;
            current = current->next;
        }
        if (current) {
            if (prev) prev->next = current->next;
            else adjacencyList[vertexA] = current->next;
            delete current;
        }
    }

    // Check if an edge exists between vertexA and vertexB
    bool edge_exist(int vertexA, int vertexB) const {
        Node* current = adjacencyList[vertexA];
        while (current) {
            if (current->vertex == vertexB) return true;
            current = current->next;
        }
        return false;
    }

    // Get the out-degree of a vertex
    int get_degree(int vertex) const {
        if (vertex < 0 || vertex >= vertices) return -1;
        int degree = 0;
        Node* current = adjacencyList[vertex];
        while (current) {
            ++degree;
            current = current->next;
        }
        return degree;
    }

    //sets vertices to variable count
    void initialize_vertices(int count) {
        vertices = count;
    }

    // Enable ++ operator to add a new vertex
    Graph& operator++() {
        Node** newadjacencyList = new Node*[vertices + 1];
        for (int i = 0; i < vertices; ++i) {
            newadjacencyList[i] = adjacencyList[i];
        }
        newadjacencyList[vertices] = nullptr;
        delete[] adjacencyList;
        adjacencyList = newadjacencyList;
        ++vertices;
        return *this;
    }

    // Enable ++ operator to add a new vertex
    Graph operator++(int) {
        Graph temp = *this;
        ++(*this);
        return temp;
    }

    // Enable -- operator to remove the last vertex
    Graph& operator--() {
        if (vertices == 0) return *this;  // Nothing to remove

        // Free the linked list for the last vertex
        Node* current = adjacencyList[vertices - 1];
        while (current) {
            Node* temp = current;
            current = current->next;
            delete temp;
        }

        // Update adjacency list (take away the vertex)
        Node** newAList = new Node*[vertices - 1];
        for (int i = 0; i < vertices - 1; ++i) {
            newAList[i] = adjacencyList[i];
        }
        delete[] adjacencyList;
        adjacencyList = newAList;

        --vertices;  // subtract vertex count by 1
        return *this;
        }

    // Check if a path exists between vertexA and vertexB
    bool path_exist(int vertexA, int vertexB) {
        if (vertexA == vertexB) return true;
        bool* visited = new bool[vertices];
        memset(visited, false, vertices * sizeof(bool));
        stack<int> s;
        s.push(vertexA);

        while (!s.empty()) {
            int current = s.top();
            s.pop();
            if (current == vertexB) {
                delete[] visited;
                return true;
            }
            visited[current] = true;
            Node* neighbor = adjacencyList[current];
            while (neighbor) {
                if (!visited[neighbor->vertex]) s.push(neighbor->vertex);
                neighbor = neighbor->next;
            }
        }
        delete[] visited;
        return false;
    }

    int connectivity_type() {
        // Check for strong connectivity
        if (is_strongly_connected()) {
            return 3;  // Strongly Connected
        }

        // Check for weak connectivity
        if (is_weakly_connected()) {
            return 1;  // Weakly Connected
        }

        // Check for unilateral connectivity
        if (is_unilaterally_connected()) {
            return 2;  // Unilaterally Connected
        }

        return 0;  // Not Connected
    }

    // Helper function to check if the graph is strongly connected
    bool is_strongly_connected() {
        for (int i = 0; i < vertices; ++i) {
            if (!path_exist(i, 0)) {
                return false;
            }
        }
        return true;
    }

    // Helper function to check if the graph is weakly connected
    bool is_weakly_connected() {
        // Create an undirected version of the graph
        for (int i = 0; i < vertices; ++i) {
            Node* current = adjacencyList[i];
            while (current) {
                // Add the reverse edge
                add_edge(current->vertex, i);
                current = current->next;
            }
        }
        // Check if the modified graph is connected
        bool* visited = new bool[vertices];
        memset(visited, false, vertices * sizeof(bool));
        queue<int> q;
        q.push(0);
        visited[0] = true;

        while (!q.empty()) {
            int current = q.front();
            q.pop();
            Node* neighbor = adjacencyList[current];
            while (neighbor) {
                if (!visited[neighbor->vertex]) {
                    q.push(neighbor->vertex);
                    visited[neighbor->vertex] = true;
                }
                neighbor = neighbor->next;
            }
        }
        for (int i = 0; i < vertices; ++i) {
            if (!visited[i]) {
                delete[] visited;
                return false;
            }
        }
        delete[] visited;
        return true;
    }

    

    // Helper function to check unilateral connectivity
    bool is_unilaterally_connected() {
        for (int i = 0; i < vertices; ++i) {
            for (int j = 0; j < vertices; ++j) {
                if (i != j && !path_exist(i, j) && !path_exist(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }


};

class Forest : public Graph {
public:
    Forest(int n = 0) : Graph(n) {
    }

    // Override add_edge to prevent cycles
    void add_edge(int vertexA, int vertexB) override {
        if (path_exist(vertexA, vertexB)) {
            cerr << "You cannot add this edge as it would create a cycle." << endl;
            return;
        }
        Graph::add_edge(vertexA, vertexB);
    }
};

class Tree : public Forest {
private:
    int root;

public:
    // Default constructor with a single vertex
    Tree() : Forest(1) {
        root = 0;  // Starting with the only vertex as root
    }

    // Add a leaf to the last added vertex
    void add_vertex(int parent) {
        if (parent < 0 || parent >= vertices) {
            cerr << "Invalid parent vertex!" << endl;
            return;
        }
        // Use Graph's ++ operator to add a vertex
        ++(*this);
        int newVertex = vertices - 1;
        add_edge(parent, newVertex);
    }

    // Set the root of the tree
    void set_root(int newRoot) {
        if (newRoot < 0 || newRoot >= vertices) {
            cerr << "Invalid root vertex!" << endl;
            return;
        }
        root = newRoot;
    }

    // Override << operator to print tree in structured format
    friend ostream& operator<<(ostream& os, const Tree& t) {
        t.printTreeStructure(os, t.root, "");
        return os;
    }

private:
    // Recursive function to print the tree in hierarchical style
    void printTreeStructure(ostream& os, int vertex, string prefix) const {
        os << prefix << vertex << endl;
        Node* current = adjacencyList[vertex];
        while (current) {
            if (current->vertex != vertex) {  // Avoid back edges in undirected graph
                printTreeStructure(os, current->vertex, prefix + "--- ");
            }
            current = current->next;
        }
    }
};



#include <iostream>
using namespace std;

// Assuming Graph, Forest, Tree classes are defined elsewhere

int main() {
    cout << "We're making a graph today using an adjacency list!\n";
    cout << "Select the type of graph:\n";
    cout << "1. Graph (Undirected)\n";
    cout << "2. Forest (No cycles)\n";
    cout << "3. Tree (Connected, acyclic)\n";
    
    int graphType;
    cin >> graphType;

    Graph* g = nullptr; // Pointer to hold the chosen graph type

    // Initialize based on the chosen type
    switch (graphType) {
        case 1:
            g = new Graph();
            break;
        case 2:
            g = new Forest();
            break;
        case 3:
            g = new Tree();
            break;
        default:
            cout << "Invalid choice, exiting.\n";
            return 0;
    }

    // Primary menu for graph operations
    cout << "Please choose one of the following options:\n 1. Add vertices\n 2. Delete graph\n";
    
    int choice, x;
    cin >> choice;

    if (choice == 1) {
        cout << "How many vertices? ";
        cin >> x;

        if (g) { // Ensure g is not null
            g->initialize_vertices(x);  // Initialize the graph with 'x' vertices
            cout << "Graph now has " << x << " vertices.\n";
        }

        // Main operation menu
        while (true) {
            cout << "\nPlease decide what to do next:\n";
            cout << "1. Add Vertex\n2. Add Edge\n3. Delete Last Vertex\n4. Delete Edge\n";
            cout << "5. Check Path Existence\n6. Check Edge Existence\n";
            cout << "7. Get Vertex Degree\n8. Print Graph\n9. Delete Graph / Exit\n";
            cin >> choice;

            switch (choice) {
                case 1:
                    if (g) {
                        ++(*g);
                        cout << "Vertex added.\n";
                    }
                    break;

                case 2: {
                    int src, dest;
                    cout << "Enter source and destination vertices: ";
                    cin >> src >> dest;
                    g->add_edge(src, dest);
                    cout << "Edge added.\n";
                    break;
                }

                case 3:
                    if (g) {
                        --(*g);
                        cout << "Last vertex removed.\n";
                    }
                    break;

                case 4: {
                    int src, dest;
                    cout << "Enter source and destination vertices: ";
                    cin >> src >> dest;
                    if (g) {
                        g->remove_edge(src, dest);
                        cout << "Edge removed.\n";
                    }
                    break;
                }

                case 5: {
                    int src, dest;
                    cout << "Enter source and destination vertices: ";
                    cin >> src >> dest;
                    if (g) {
                        bool exists = g->path_exist(src, dest);
                        cout << (exists ? "Path exists.\n" : "Path does not exist.\n");
                    }
                    break;
                }

                case 6: {
                    int src, dest;
                    cout << "Enter source and destination vertices: ";
                    cin >> src >> dest;
                    if (g) {
                        bool edgeExists = g->edge_exist(src, dest);
                        cout << (edgeExists ? "Edge exists.\n" : "Edge does not exist.\n");
                    }
                    break;
                }

                case 7: {
                    int vertex;
                    cout << "Which vertex? ";
                    cin >> vertex;
                    if (g) {
                        int degree = g->get_degree(vertex);
                        cout << "Degree of vertex " << vertex << " is " << degree << ".\n";
                    }
                    break;
                }

                case 8:
                    if (g) {
                        cout << *g << endl; // Assuming operator<< is overloaded
                    }
                    break;

                case 9:
                    cout << "Graph has been deleted. Goodbye!\n";
                    delete g; // Free allocated memory
                    return 0;

                default:
                    cout << "Invalid choice! Try again.\n";
            }
        }
    } 
    else {
        cout << "Graph was deleted. Goodbye!\n";
        delete g; // Free allocated memory
        return 0;
    }

    return 0;
}

