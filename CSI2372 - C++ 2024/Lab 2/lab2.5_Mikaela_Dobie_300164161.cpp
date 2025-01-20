//Mikaela Dobie 300164161
#include <iostream>
#include <tuple>
using namespace std;

class Colour {
public:
    // Nested structure for RGB values
    struct RGB {
        int r;
        int g;
        int b;
    };

    using RGBcolour = RGB;

private:
    RGBcolour colour;

public:
    Colour(int r, int g, int b) : colour{r, g, b} {}

    void blend(const Colour& other) {
        colour.r = (colour.r + other.colour.r) / 2;
        colour.g = (colour.g + other.colour.g) / 2;
        colour.b = (colour.b + other.colour.b) / 2;
    }

    void display() const {
        cout << "RGB(" << colour.r << ", " << colour.g << ", " << colour.b << ") \n";
    }
};

int main() {
    Colour colour1(255, 0, 0); // Red
    Colour colour2(0, 0, 255); // Blue

    cout << "Original Colours: \n";
    colour1.display();
    colour2.display();

    colour1.blend(colour2);

    cout << "Blended Colour: \n";
    colour1.display();

    return 0;
}
